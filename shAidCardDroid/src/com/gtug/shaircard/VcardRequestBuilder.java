package com.gtug.shaircard;

import android.content.Context;

import com.stanfy.serverapi.request.ListRequestBuilder;
import com.stanfy.serverapi.request.Operation;

/**
 * Request builder for {@link OurOperation#GET_ALL_EVENTS} operation.
 */
public class VcardRequestBuilder extends ListRequestBuilder {

	public VcardRequestBuilder(final Context context, Integer id) {
		super(context);
		
		addSimpleParameter("eventId", id.toString());
	}

	@Override
	public Operation getOperation() {
		return OurOperation.GET_ALL_VCARDS_BY_EVENTID;
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
