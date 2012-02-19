package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.gtug.shaircard.model.Event;
import com.stanfy.app.activities.OneRequestModelActivity;
import com.stanfy.serverapi.request.Operation;
import com.stanfy.serverapi.request.ParameterValue;
import com.stanfy.serverapi.request.RequestBuilder;
import com.stanfy.serverapi.response.json.GsonBasedResponseHandler;
import com.stanfy.views.list.ListView;
import com.stanfy.views.list.ModelListAdapter;

class UpdateRequestBuilder extends RequestBuilder {

	public UpdateRequestBuilder(ShAirCardDroidActivity context) {
		super(context);
		ParameterValue value = new ParameterValue();
		value.setName("value");
		try {
			value.setValue(GsonBasedResponseHandler.GBUILDER.create().toJson(
					context.getApp().getFavorites()));
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

	@Override
	public Operation getOperation() {
		// TODO Auto-generated method stub
		return OurOperation.REFRESH_EVENTS;
	}

}

public class ShAirCardDroidActivity
		extends
		OneRequestModelActivity<shAirCardApp, UpdateRequestBuilder, ArrayList<Event>> {

	ListView listView;

	shAirCardApp app;

	private ModelListAdapter<Event> adapter;

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

		adapter = new ModelListAdapter<Event>(this,
				EventListFragment
						.createRenderer((shAirCardApp) getApplication()));

	}

	@Override
	protected void onResume() {
		super.onResume();
		update(null);

		fetch();

		// // Get last known location
		// Location loc = GPSLocation.getLastKnown(this);
		// if (loc != null) {
		// //TODO PUT THE REQUEST HERE
		// Event closestEvent = new Event();
		// if (closestEvent.getId() != -1) {
		// //TODO CALL DIALOG HERE
		// }
		// }

	}

	public void update(View view) {
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

	@Override
	public UpdateRequestBuilder createRequestBuilder() {
		// TODO Auto-generated method stub
		return new UpdateRequestBuilder(this);
	}

	@Override
	public Class<?> getModelClass() {
		// TODO Auto-generated method stub
		return ArrayList.class;
	}

	@Override
	public boolean processModel(ArrayList<Event> data) {
		// TODO Auto-generated method stub
		adapter.replace(data);
		return false;
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