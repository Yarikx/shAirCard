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

//		Event e1 = new Event();
//		e1.setLongitude(20.0);
//		e1.setLatitude(30.0);
//		
//		Event e2 = new Event();
//		e2.setLongitude(50.0);
//		e2.setLatitude(60.0);
//		
//		EntityManager em = EMFService.get().createEntityManager();
//		em.persist(e1);
//		em.close();
//		
//		em = EMFService.get().createEntityManager();
//		em.persist(e2);
//		em.close();
//		//////
		
		EntityManager em2 = EMFService.get().createEntityManager();
		Query q = em2.createQuery("SELECT e FROM Event e");
		List<Event> eList = q.getResultList();
		
		resp.getWriter().println(Event.listToJson(eList));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
