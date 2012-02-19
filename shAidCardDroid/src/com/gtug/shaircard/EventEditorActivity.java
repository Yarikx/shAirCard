package com.gtug.shaircard;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.gtug.shaircard.model.Event;
import com.stanfy.app.activities.OneRequestModelActivity;

public class EventEditorActivity extends
		OneRequestModelActivity<shAirCardApp, EventSendRequestBuilder, Event> {

	EditText name, desc, location, password;
	Button startDate, startTime, endDate, endTime;
	private Event event;
	static final int START_DATE_DIALOG = 0;
	static final int START_TIME_DIALOG = 1;
	static final int END_DATE_DIALOG = 2;
	static final int END_TIME_DIALOG = 3;

	final DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			GregorianCalendar date = new GregorianCalendar();
			date.set(Calendar.YEAR, year);
			date.set(Calendar.MONTH, monthOfYear);
			date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			event.setTimeBegin(date.getTime());
			updateTimeButtons();
		}
	};

	final DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			GregorianCalendar date = new GregorianCalendar();
			date.set(Calendar.YEAR, year);
			date.set(Calendar.MONTH, monthOfYear);
			date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			event.setTimeEnd(date.getTime());
			updateTimeButtons();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_editor_layout);

		event = new Event();

		name = (EditText) findViewById(R.id.name);
		desc = (EditText) findViewById(R.id.description);
		location = (EditText) findViewById(R.id.location);
		password = (EditText) findViewById(R.id.password);
		startDate = (Button) findViewById(R.id.startDate);
		startTime = (Button) findViewById(R.id.startTime);
		endDate = (Button) findViewById(R.id.endDate);
		endTime = (Button) findViewById(R.id.endTime);

		startDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(START_DATE_DIALOG);
			}
		});

		// TODO DELETE WHEN DONE NORMAL LOADING!!!
		updateTimeButtons();

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case START_DATE_DIALOG:
			return new DatePickerDialog(this, startDateListener, 
					event.getTimeBegin().getYear() - 1970, event.getTimeBegin().getMonth() - 1,
					event.getTimeBegin().getDay() - 1);
		}
		return null;

	}

	public void updateTimeButtons() {
		startDate
				.setText(DateFormat.format("dd.MM.yyyy", event.getTimeBegin()));
		startTime.setText(DateFormat.format("hh:mm:SS", event.getTimeBegin()));
		endDate.setText(DateFormat.format("dd.MM.yyyy", event.getTimeEnd()));
		endTime.setText(DateFormat.format("hh:mm:SS", event.getTimeEnd()));
	}

	public void save(View view) {
		event.setName(name.getText().toString());
		event.setDescription(desc.getText().toString());
		event.setAddress(location.getText().toString());
		event.setPassword(password.getText().toString());

		event.setCreatorId(getApp().deviceId);

		fetch();
		// TODO add to favorites

	}

	@Override
	public EventSendRequestBuilder createRequestBuilder() {
		return new EventSendRequestBuilder(this, event);
	}

	@Override
	public Class<?> getModelClass() {
		return Event.class;
	}

	@Override
	public boolean processModel(Event data) {
		Log.d("callback", "IM FUCKING LOVE COCAINE");
		shAirCardApp app = getApp();
		app.addFavorite(data);
		finish();
		return true;
	}

}
