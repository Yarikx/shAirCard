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
import com.gtug.shaircard.model.VCard;

public class AddEvent extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/json");
		resp.setCharacterEncoding("UTF-8");
		String paramName = (String)req.getParameterNames().nextElement();
		String body = req.getParameterValues(paramName)[0];

		Gson gson = new Gson();

		Event e = gson.fromJson(body, Event.class);
		if (e.getPassword() != null) {
			e.setUsePassword(true);
		} else {
			e.setUsePassword(false);
		}
		
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(e);
		em.close();
		
		resp.getWriter().println(e.toJson());
	}

}
