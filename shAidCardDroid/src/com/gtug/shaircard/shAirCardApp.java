package com.gtug.shaircard;

import java.util.ArrayList;

import android.telephony.TelephonyManager;

import com.google.gson.reflect.TypeToken;
import com.gtug.shaircard.model.Event;
import com.stanfy.app.Application;
import com.stanfy.serverapi.RequestMethodHelper;
import com.stanfy.serverapi.request.RequestDescription;
import com.stanfy.serverapi.response.ParserContext;
import com.stanfy.serverapi.response.json.OneClassModelParserContext;

public class shAirCardApp extends Application {

	/** Application authority for content provider configuration. */
	public static final String APP_AUTHORITY = "com.gtug.shaircard";

	public String deviceId;
	public TelephonyManager manager;

	@Override
	public void onCreate() {
		super.onCreate();
		setImagesDAOAuthority(APP_AUTHORITY);
		manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		deviceId = manager.getDeviceId();
	}

	@Override
	protected RequestMethodHelper createRequestMethodHelper() {
		return new RequestMethodProvider();
	}

	/**
	 * Factory for request descriptions and parser contexts.
	 */
	private static class RequestMethodProvider extends RequestMethodHelper {

		public RequestMethodProvider() {
			super(TYPE_JSON, APP_AUTHORITY);
		}

		@Override
		public ParserContext createParserContext(
				final RequestDescription requestDescription) {
			switch (OurOperation.byCode(requestDescription.getOperationCode())) {
			case GET_FILTERED_EVENTS:
				return OneClassModelParserContext
						.create(new TypeToken<ArrayList<Event>>() {
						});
			default:
				return super.createParserContext(requestDescription);
			}
		}

	}

}
