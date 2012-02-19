package com.gtug.shaircard.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gtug.shaircard.model.EMFService;
import com.gtug.shaircard.model.Event;
import com.gtug.shaircard.model.VCard;


public class GetAllEvents extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		
		EntityManager em2 = EMFService.get().createEntityManager();
		Query q = em2.createQuery("SELECT e FROM Event e");
		List<Event> eList = q.getResultList();
		
		resp.getWriter().println(Event.listToJson(eList));
	}

}
