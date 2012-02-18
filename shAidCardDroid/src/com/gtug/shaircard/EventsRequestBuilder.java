package com.gtug.shaircard;

import android.content.Context;

import com.stanfy.serverapi.request.ListRequestBuilder;
import com.stanfy.serverapi.request.Operation;

/**
 * Request builder for {@link OurOperation#GET_ALL_EVENTS} operation.
 */
public class EventsRequestBuilder extends ListRequestBuilder {

	public EventsRequestBuilder(final Context context) {
		super(context);
	}

	public EventsRequestBuilder setText(String text) {
		addSimpleParameter("requestText", text);
		return this;
	}

	@Override
	public Operation getOperation() {
		return OurOperation.GET_FILTERED_EVENTS;
	}

	@Override
	public ListRequestBuilder setOffset(final int offset) {
		return super.setOffset(offset);
	}

	@Override
	public String getOffsetParamName() {
		return "page";
	}

}
