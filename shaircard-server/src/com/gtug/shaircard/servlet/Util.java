package com.gtug.shaircard.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class Util {

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
}
