package com.gtug.shaircard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stanfy.app.activities.OneFragmentActivity;

public class SearchEventsListActivity extends OneFragmentActivity<shAirCardApp>{
	
	
	EditText searchField;
	Button okButton;
	EventListFragment fragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		searchField = (EditText) findViewById(R.id.search_field);
		okButton = (Button) findViewById(R.id.ok);
	}
	
	public void update(View view){
		fragment.update(searchField.getText().toString(), null, null);
	}

	@Override
	protected Fragment createFragment(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		fragment = new EventListFragment();
		return fragment;
	}

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.search_events_activity;
	}
	
}
