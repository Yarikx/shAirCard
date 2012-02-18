package com.gtug.shaircard.model;

import com.google.gson.Gson;

public class Jsonable {

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
