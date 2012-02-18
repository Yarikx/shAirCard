package com.gtug.shaircard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.stanfy.app.activities.OneFragmentActivity;

public class ShAirCardDroidActivity extends OneFragmentActivity<shAirCardApp> {

	private ListView eventsListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// eventsListView = (ListView) findViewById(R.id.eventList);
	}

	public void update(View view) {

	}

	public void searchEvents(View view) {
		startActivity(new Intent(this, SearchEventsListActivity.class));
	}

	@Override
	protected Fragment createFragment(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return new EventListFragment();
	}

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.main;
	}
}