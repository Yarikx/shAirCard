package com.gtug.shaircard.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gtug.shaircard.model.EMFService;
import com.gtug.shaircard.model.Event;

public class AddEvent extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/json");
		String body = Util.getPostBody(req);
		Gson gson = new Gson();
		Event e = gson.fromJson(body, Event.class);
		
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(e);
		em.close();
		
		resp.getWriter().println(e.toJson());
	}

}
