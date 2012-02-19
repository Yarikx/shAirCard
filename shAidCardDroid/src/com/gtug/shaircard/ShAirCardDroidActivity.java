package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.gtug.shaircard.model.Event;
import com.stanfy.app.activities.OneRequestModelActivity;
import com.stanfy.serverapi.request.Operation;
import com.stanfy.serverapi.request.ParameterValue;
import com.stanfy.serverapi.request.RequestBuilder;
import com.stanfy.serverapi.response.ResponseData;
import com.stanfy.serverapi.response.json.GsonBasedResponseHandler;
import com.stanfy.utils.ApiMethodsSupport.ApiSupportRequestCallback;
import com.stanfy.views.list.ListView;
import com.stanfy.views.list.ModelListAdapter;

class UpdateRequestBuilder extends RequestBuilder {

	public UpdateRequestBuilder(ShAirCardDroidActivity context) {
		super(context);
		ParameterValue value = new ParameterValue();
		value.setName("value");

		List<Event> fav;
		fav = context.getApp().getFavorites();

		ArrayList<Long> integers = new ArrayList<Long>();
		for (Event event : fav) {
			integers.add(event.getId());
		}

		value.setValue(GsonBasedResponseHandler.GBUILDER.create().toJson(
				integers));

		addParameter(value);

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

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				
				getApp().removeFavorite((Event) adapter.getItemAtPosition(position));
				update(null);
				
				
				return true;
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

		// Get last known location
		final Location loc = GPSLocation.getLastKnown(this);
		if (loc != null) {

			new RequestBuilder(this) {

				{
					addSimpleParameter("latitude", "" + loc.getLatitude());
					addSimpleParameter("longitude", "" + loc.getLongitude());
				}

				@Override
				public Operation getOperation() {
					return OurOperation.GET_CLOSEST_EVENT;
				}
			}.execute();

		}

	}

	@Override
	public ApiSupportRequestCallback<ArrayList<Event>> createRequestCallback() {
		return new ModelRequestCallback<ArrayList<Event>>(this) {
			@Override
			public boolean filterOperation(int token, int o) {

				if (o == OurOperation.GET_CLOSEST_EVENT.getCode()
						|| o == OurOperation.REFRESH_EVENTS.getCode()) {
					return true;
				} else {
					return super.filterOperation(token, o);
				}
			}

			@Override
			protected void processSuccessUnknownModelType(int token,
					int operation, ResponseData responseData, Serializable model) {
				// TODO Auto-generated method stub

				final Event event = (Event) model;

				if (event.getId() != -1
						&& !getApp().getFavorites().contains(event)) {

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub

							AlertDialog.Builder builder = new AlertDialog.Builder(
									ShAirCardDroidActivity.this);

							builder.setTitle(R.string.main_question);
							builder.setMessage(event.getName() + "\n"
									+ event.getAddress());
							builder.setPositiveButton(android.R.string.yes,
									new OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											getApp().addFavorite(event);
											update(null);
											dialog.dismiss();

										}
									});

							builder.setNegativeButton(android.R.string.no,
									new OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();

										}
									});

							builder.create().show();

						}
					});

				}

			}

		};
		// return super.createRequestCallback();

	}

	public void update(View view) {
		adapter.replace(app.getFavorites());
		listView.setAdapter(adapter);
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
		try {
			getApp().setFavorites(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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