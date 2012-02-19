package com.gtug.shaircard.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gtug.shaircard.servlet.Util;

public class Jsonable {

	public String toJson() {
		Gson gson = Util.conjureGson();
		return gson.toJson(this);
	}
	
}
