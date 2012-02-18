package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

import com.gtug.shaircard.model.Event;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EventActivity extends Activity {

	TextView name, address, count;
	private Event event;

	shAirCardApp app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		name = (TextView) findViewById(R.id.name);
		address = (TextView) findViewById(R.id.address);
		count = (TextView) findViewById(R.id.count);

		app = (shAirCardApp) getApplication();

		try {
			event = (Event) getIntent().getExtras().getSerializable("event");
			name.setText(event.getName());
			address.setText(event.getAddress());
			count.setText("" + event.getPeopleCount());
		} catch (Exception e) {
			finish();
		}

	}

	public void subscribe(View view) {
		try {
			app.addFavorite(event);
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
