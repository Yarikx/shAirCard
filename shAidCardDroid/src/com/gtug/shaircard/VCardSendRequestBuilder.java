package com.gtug.shaircard;

import android.content.Context;

import com.gtug.shaircard.model.VCard;
import com.stanfy.serverapi.request.Operation;
import com.stanfy.serverapi.request.ParameterValue;
import com.stanfy.serverapi.request.RequestBuilder;
import com.stanfy.serverapi.response.json.GsonBasedResponseHandler;

public class VCardSendRequestBuilder extends RequestBuilder {

	public VCardSendRequestBuilder(final Context context, VCard card) {
		super(context);
		ParameterValue value = new ParameterValue();
		value.setName("value");
		value.setValue(GsonBasedResponseHandler.GBUILDER.create().toJson(card));
		addParameter(value);
	}

	@Override
	public Operation getOperation() {
		return OurOperation.POST_VCARD;
	}

}
