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
import com.gtug.shaircard.model.VCard;

public class GetAllVCardsByEventId extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		EntityManager em = EMFService.get().createEntityManager();
		String eventIdStr = req.getParameter("eventId");
		String password = req.getParameter("password");
		String pageStr = req.getParameter("page");
		double lat = 0, lon = 0;
		int page = 0, from = 0, to = 29;
		long eventId;
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		if (page != 0) {
			from = 30 + page * 10;
			to = from + 9;
		}
		if (eventIdStr == null) {
			resp.getWriter().println("FAILURE: No ivent ID");
			return;
		} else {
			eventId = Long.parseLong(eventIdStr);
		}
		Query qGetEvent = em.createQuery("SELECT e FROM Event e WHERE e.id = :id");
		qGetEvent.setParameter("id", eventId);
		
		Event event = (Event)qGetEvent.getSingleResult();
		if (false && event.getPassword() != null && !event.getPassword().equals("") && !event.getPassword().equals(password)) {
			resp.getWriter().println("FAILURE: Wrong event password");
			return;
		}
		Query q = em.createQuery("SELECT c FROM VCard c WHERE c.eventId = :eventid");
		q.setParameter("eventid", eventId);
		List<VCard> cList = q.getResultList();
		
		from = Math.min(from, cList.size());
		to = Math.min(to, cList.size());
		
		resp.getWriter().println(VCard.listToJson(cList.subList(from, to)));
	}

}
