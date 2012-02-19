package com.gtug.shaircard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.gtug.shaircard.model.Event;
import com.stanfy.app.activities.OneRequestModelActivity;

public class EventEditorActivity extends
		OneRequestModelActivity<shAirCardApp, EventSendRequestBuilder, Event> {

	EditText name, desc, location, password;
	private Event event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_editor_layout);

		name = (EditText) findViewById(R.id.name);
		desc = (EditText) findViewById(R.id.description);
		location = (EditText) findViewById(R.id.location);
		password = (EditText) findViewById(R.id.password);

	}

	public void save(View view) {
		event = new Event();
		event.setName(name.getText().toString());
		event.setDescription(name.getText().toString());
		event.setAddress(name.getText().toString());
		event.setPassword(name.getText().toString());

		event.setCreatorId(getApp().deviceId);

		fetch();
		// TODO add to favorites
		finish();

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
		return false;
	}

}
