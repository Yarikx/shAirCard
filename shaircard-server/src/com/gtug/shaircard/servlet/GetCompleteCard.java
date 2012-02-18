package com.gtug.shaircard.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gtug.shaircard.model.EMFService;
import com.gtug.shaircard.model.Event;
import com.gtug.shaircard.model.VCard;
import com.gtug.shaircard.model.VCardImage;

public class GetCompleteCard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cardIdStr = req.getParameter("vcardId");
		String password = req.getParameter("password");
		long cardId;
		if (cardIdStr == null) {
			resp.getWriter().println("FAILURE: No vcard ID");
			return;
		} else {
			cardId = Long.parseLong(cardIdStr);
		}
		EntityManager em = EMFService.get().createEntityManager();
		Query qGetCard = em.createQuery("SELECT c FROM VCard c WHERE c.id = :id");
		qGetCard.setParameter("id", cardId);
		VCard card = (VCard)qGetCard.getSingleResult();
		
		Query qGetEvent = em.createQuery("SELECT e FROM Event e WHERE e.id = :id");
		qGetEvent.setParameter("id", card.getEventId());
		Event event = (Event)qGetEvent.getSingleResult();
		
		em.close();
		
		if (event.getPassword() != null && !event.getPassword().equals("") && !event.getPassword().equals(password)) {
			resp.getWriter().println("FAILURE: Wrong event password");
			return;
		}
		
		resp.getWriter().println(card.toJson());
	}
	
}
