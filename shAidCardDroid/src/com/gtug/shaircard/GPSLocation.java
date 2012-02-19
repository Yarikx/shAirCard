package com.gtug.shaircard;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPSLocation {

	public static Location getLastKnown(Activity a) {
		LocationManager locationManager = (LocationManager)a.getSystemService(Activity.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		String bestProvider = locationManager.getBestProvider(criteria, false);
		
		return locationManager.getLastKnownLocation(bestProvider);
	}
}
