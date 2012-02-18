package com.gtug.shaircard;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.gtug.shaircard.model.Event;
import com.stanfy.app.fragments.list.FetchingListFragment;
import com.stanfy.images.ImagesManagerContext;
import com.stanfy.views.list.Fetcher;
import com.stanfy.views.list.ModelListAdapter.ElementRenderer;
import com.stanfy.views.list.PageFetcher;

public class EventListFragment extends
		FetchingListFragment<shAirCardApp, Event> {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RENDERER = new ElementRenderer<Event>(R.layout.event_list_item) {
			@Override
			public void render(final Adapter adapter, final ViewGroup parent,
					final Event element, final View view, final Object holder,
					final int position) {
				final EventHolder h = (EventHolder) holder;
				h.name.setText(element.getName());
				h.location.setText(element.getAddress());
				h.admin.setVisibility(EventListFragment.this.getOwnerActivity()
						.getApp().deviceId.equals(element.getCreatorId()) ? View.VISIBLE
						: View.INVISIBLE);

			}

			@Override
			public Object createHolder(final View view,
					final ImagesManagerContext<?> imagesManagerContext) {
				final EventHolder h = new EventHolder();
				h.name = (TextView) view.findViewById(R.id.name);
				h.location = (TextView) view.findViewById(R.id.location);
				h.admin = view.findViewById(R.id.admin);
				h.memberCount = (TextView) view.findViewById(R.id.memersCount);

				return h;
			}
		};

	}

	@Override
	protected ElementRenderer<Event> createRenderer() {
		return RENDERER;
	}

	private ElementRenderer<Event> RENDERER;

	@Override
	public Fetcher<Event> createAdapter(final Context context,
			final ElementRenderer<Event> renderer) {
		return new PageFetcher<Event>(context, renderer, getRequestToken());
	}

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRequestBuilder(new EventsRequestBuilder(getOwnerActivity()));
	}

	public void update(String text, Float longtitude, Float latitude) {

		EventsRequestBuilder builder = new EventsRequestBuilder(
				getOwnerActivity()).setText(text);
		if (longtitude != null) {
			// TODO
		}

		setRequestBuilder(builder);

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> view, View arg1,
					int position, long arg3) {
				Event event = (Event) view.getItemAtPosition(position);
				

			}
		});
	}

}
