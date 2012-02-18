package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
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

	private static final String FAVORITES_FILENAME = "favorites.dat";

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
			case GET_ALL_VCARDS_BY_EVENTID:
				return OneClassModelParserContext
						.create(new TypeToken<ArrayList<Event>>() {
						});
			default:
				return super.createParserContext(requestDescription);
			}
		}

	}

	public void addFavorite(Event event) throws StreamCorruptedException,
			IOException, ClassNotFoundException {
		ArrayList<Event> favorites;
		try {
			favorites = getFavorites();
		} catch (FileNotFoundException e) {
			favorites = new ArrayList<Event>();
		}
		favorites.add(event);
		setFavorites(favorites);
	}

	public void removeFavorite(Event event) throws StreamCorruptedException,
			FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Event> favorites = getFavorites();
		favorites.remove(event);
		setFavorites(favorites);
	}

	public ArrayList<Event> getFavorites() throws StreamCorruptedException,
			FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				openFileInput(FAVORITES_FILENAME));
		ArrayList<Event> events = (ArrayList<Event>) ois.readObject();
		ois.close();
		return events;
	}

	public void setFavorites(ArrayList<Event> events)
			throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(
				FAVORITES_FILENAME, MODE_PRIVATE));
		oos.writeObject(events);
		oos.close();
	}
}
