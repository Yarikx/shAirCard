package com.gtug.shaircard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ShAirCardDroidActivity extends Activity {
	
	private ListView eventsListView;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		eventsListView = (ListView) findViewById(R.id.eventList);
	}

	public void update(View view) {
		
	}
}