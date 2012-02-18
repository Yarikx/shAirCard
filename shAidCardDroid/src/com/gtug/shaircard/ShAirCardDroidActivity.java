package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gtug.shaircard.model.Event;
import com.stanfy.views.list.ListView;
import com.stanfy.views.list.ModelListAdapter;
import com.stanfy.views.list.OneTimeLoadAdapter;

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

	}

	@Override
	protected void onResume() {
		super.onResume();
		update(null);

	}

	public void update(View view) {
		ModelListAdapter<Event> adapter = new ModelListAdapter<Event>(this,
				EventListFragment
						.createRenderer((shAirCardApp) getApplication()));

		try {
			adapter.replace(app.getFavorites());
			listView.setAdapter(adapter);
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

	public void searchEvents(View view) {
		startActivity(new Intent(this, SearchEventsListActivity.class));
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