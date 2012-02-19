package com.gtug.shaircard;

import android.content.Context;

import com.gtug.shaircard.model.Event;
import com.stanfy.serverapi.request.Operation;
import com.stanfy.serverapi.request.ParameterValue;
import com.stanfy.serverapi.request.RequestBuilder;
import com.stanfy.serverapi.response.json.GsonBasedResponseHandler;

public class EventSendRequestBuilder extends RequestBuilder {

	public EventSendRequestBuilder(final Context context, Event event) {
		super(context);
		setEvent(event);
	}

	public void setEvent(Event event) {
		ParameterValue value = new ParameterValue();
		value.setName("value");
		value.setValue(GsonBasedResponseHandler.GBUILDER.create().toJson(event));
		addParameter(value);
	}

	@Override
	public Operation getOperation() {
		return OurOperation.POST_EVENT;
	}

}
