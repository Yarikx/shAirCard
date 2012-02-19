package com.gtug.shaircard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.telephony.TelephonyManager;

import com.google.gson.reflect.TypeToken;
import com.gtug.shaircard.model.Event;
import com.gtug.shaircard.model.VCard;
import com.stanfy.app.Application;
import com.stanfy.serverapi.RequestMethodHelper;
import com.stanfy.serverapi.request.RequestDescription;
import com.stanfy.serverapi.response.ParserContext;
import com.stanfy.serverapi.response.json.OneClassModelParserContext;

public class shAirCardApp extends Application {

	/** Application authority for content provider configuration. */
	public static final String APP_AUTHORITY = "com.gtug.shaircard";

	private static final String FAVORITES_FILENAME = "favorites.dat";

	private static final String MY_VCARDS_FILENAME = "vcards.dat";

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
			case GET_ALL_VCARDS_BY_EVENTID:
				return OneClassModelParserContext
						.create(new TypeToken<ArrayList<VCard>>() {
						});
			case POST_EVENT:
			case GET_CLOSEST_EVENT:
				return OneClassModelParserContext
						.create(new TypeToken<Event>() {
						});
			case REFRESH_EVENTS:
				return OneClassModelParserContext
						.create(new TypeToken<ArrayList<Event>>() {
						});
				// case POST_VCARD:
				// return OneClassModelParserContext
				// .create(new TypeToken<ArrayList<VCard>>() {
				// });
			default:
				return super.createParserContext(requestDescription);
			}
		}

	}

	public void addFavorite(Event event) {
		ArrayList<Event> favorites;
		try {
			favorites = getFavorites();
			favorites.add(event);
			setFavorites(favorites);
		} catch (FileNotFoundException e) {
			favorites = new ArrayList<Event>();
		}

	}

	public void removeFavorite(Event event) {
		ArrayList<Event> favorites = getFavorites();
		favorites.remove(event);
		try {
			setFavorites(favorites);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Event> getFavorites() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(openFileInput(FAVORITES_FILENAME));
			ArrayList<Event> events = (ArrayList<Event>) ois.readObject();
			ois.close();
			return events;
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
		return new ArrayList<Event>();

	}

	public void setFavorites(ArrayList<Event> events)
			throws FileNotFoundException {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(openFileOutput(FAVORITES_FILENAME,
					MODE_PRIVATE));
			oos.writeObject(events);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<VCard> getMyVcards() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(openFileInput(MY_VCARDS_FILENAME));
			ArrayList<VCard> res = (ArrayList<VCard>) ois.readObject();
			ois.close();
			return res;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<VCard>();
		}

	}

	public void setMyVcards(ArrayList<VCard> vcards)
			throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(
				MY_VCARDS_FILENAME, MODE_PRIVATE));
		oos.writeObject(vcards);
		oos.close();
	}
}
