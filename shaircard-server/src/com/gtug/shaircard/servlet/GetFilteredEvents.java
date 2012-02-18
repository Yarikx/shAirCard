package com.gtug.shaircard.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gtug.shaircard.model.EMFService;
import com.gtug.shaircard.model.Event;

public class GetFilteredEvents extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String requestText = req.getParameter("requestText");
		if (requestText == null) {
			resp.getWriter().println("FAILURE: No request text provided");
			return;
		}
		String[] keywords = requestText.split(",");
		StringBuilder sqlReq = new StringBuilder("SELECT e FROM Event e WHERE ");
		for (int i = 0; i < keywords.length; i++) {
			String s = keywords[i];
			sqlReq.append("e.name LIKE '" + s + 
					"%' OR e.description LIKE '" + s + 
					"%' OR e.address LIKE '" + s + "%'");
			if (i != keywords.length - 1) {
				sqlReq.append(" OR ");
			}
		}
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery(sqlReq.toString()).setMaxResults(20);
		System.out.println("reqtext: " + requestText);
		System.out.println(sqlReq.toString());
		List<Event> eList = q.getResultList();
		resp.getWriter().println(Event.listToJson(eList));
	}

}
