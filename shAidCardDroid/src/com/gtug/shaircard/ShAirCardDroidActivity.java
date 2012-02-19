package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.gtug.shaircard.model.Event;
import com.stanfy.views.list.ListView;
import com.stanfy.views.list.ModelListAdapter;

public class ShAirCardDroidActivity extends Activity {

	ListView listView;

	shAirCardApp app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		listView = (ListView) findViewById(R.id.events_list);

		app = (shAirCardApp) getApplication();

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				Event event = (Event) adapter.getItemAtPosition(position);
				Intent intent = new Intent(ShAirCardDroidActivity.this,
						EventVcardsActivity.class);
				intent.putExtra("event", event);
				startActivity(intent);

			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		update(null);
		
		// Get last known location
		Location loc = GPSLocation.getLastKnown(this);
		if (loc != null) {
			//TODO PUT THE REQUEST HERE
			Event closestEvent = new Event();
			if (closestEvent.getId() != -1) {
				//TODO CALL DIALOG HERE
			}
		}

		
	}

	public void update(View view) {
		ModelListAdapter<Event> adapter = new ModelListAdapter<Event>(this,
				EventListFragment
						.createRenderer((shAirCardApp) getApplication()));

		try {
			adapter.replace(app.getFavorites());
			listView.setAdapter(adapter);

		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void searchEvents(View view) {
		startActivity(new Intent(this, SearchEventsListActivity.class));
	}
	
	public void addEvent(View view) {
		startActivity(new Intent(this, EventEditorActivity.class));
	}

	public void openManager(View view) {
		startActivity(new Intent(this, VCardManagerActivity.class));
	}

	// @Override
	// protected Fragment createFragment(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// return new EventListFragment();
	// }
	//
	// @Override
	// protected int getLayoutId() {
	// // TODO Auto-generated method stub
	// return R.layout.main;
	// }
}