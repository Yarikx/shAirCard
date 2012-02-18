package com.gtug.shaircard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gtug.shaircard.model.Event;
import com.stanfy.app.activities.OneFragmentActivity;

public class EventVcardsActivity extends OneFragmentActivity<shAirCardApp> {

	TextView name;
	TextView location;
	TextView count;
	VCardListFragment fragment;

	Event event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		try {
			event = (Event) getIntent().getExtras().getSerializable("event");
		} catch (Exception e) {
			finish();
		}
		super.onCreate(savedInstanceState);

		name = (TextView) findViewById(R.id.name);
		location = (TextView) findViewById(R.id.location);
		count = (TextView) findViewById(R.id.memersCount);

	}

	// public void update(View view) {
	// fragment.update(searchField.getText().toString(), null, null);
	// }

	@Override
	protected Fragment createFragment(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		fragment = new VCardListFragment(event);
		return fragment;
	}

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.event_vcards_layout;
	}

}
