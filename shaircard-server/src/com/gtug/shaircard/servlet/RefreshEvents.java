package com.gtug.shaircard.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gtug.shaircard.model.EMFService;
import com.gtug.shaircard.model.Event;

public class RefreshEvents extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");

		// String body = Util.getPostBody(req);
//		Logger log = Logger.getLogger(getServletName());
		String paramName = (String) req.getParameterNames().nextElement();
		String body = req.getParameterValues(paramName)[0];

		Gson gson = Util.conjureGson();
		List<Double> eList = gson.fromJson(body, ArrayList.class);

		EntityManager em = EMFService.get().createEntityManager();

		List<Event> returnList = new ArrayList<Event>();
		for (Double e : eList) {
			Query q = em.createQuery("SELECT e FROM Event e WHERE e.id = :id");
			q.setParameter("id", Math.round(e));
			List<Event> eventList = q.getResultList();
			if (!eventList.isEmpty()) {
				returnList.add(eventList.get(0));
			}

		}

		resp.getWriter().println(Event.listToJson(returnList));

	}

}
