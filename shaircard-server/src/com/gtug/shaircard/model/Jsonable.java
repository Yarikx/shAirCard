package com.gtug.shaircard.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Jsonable {

	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss Z").create();
		return gson.toJson(this);
	}
	
}
