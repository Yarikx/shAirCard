package com.gtug.shaircard.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gtug.shaircard.model.Event;
import com.gtug.shaircard.model.Jsonable;
import com.gtug.shaircard.model.VCard;

public class Util {

	public static double MaxLatRange = 1;
	public static double MaxLonRange = 1;
	public static double CloseLatRange = 0.1;
	public static double CloseLonRange = 0.1;
	
	public static String getPostBody(HttpServletRequest request) {
		BufferedReader reader;
		try {
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
		    String line = reader.readLine();
		    while (line != null) {
		        sb.append(line + "\n");
		        line = reader.readLine();
		    }
		    reader.close();

		    return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("All fucked up");
		}
	}
	
	public static Gson conjureGson() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss Z").create();
	}
}
