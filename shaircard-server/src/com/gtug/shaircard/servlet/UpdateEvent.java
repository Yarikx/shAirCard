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

public class UpdateEvent extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json");
		String paramName = (String)req.getParameterNames().nextElement();
		String body = req.getParameterValues(paramName)[0];

		Gson gson = Util.conjureGson();
		Event e = gson.fromJson(body, Event.class);
		
		EntityManager em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT e FROM Event e WHERE e.id = :id");
		q.setParameter("id", e.getId());
		Event dbEvent = (Event)q.getSingleResult();
		
		if (e.getCreatorId().equals(dbEvent.getCreatorId())) {
			Event.copyData(e, dbEvent);
			em.getTransaction().commit();
			resp.getWriter().println("SUCCESS");
		} else {
			em.getTransaction().commit();
			resp.getWriter().println("FAILURE: Wrong creator ID");
		}
	}

}
