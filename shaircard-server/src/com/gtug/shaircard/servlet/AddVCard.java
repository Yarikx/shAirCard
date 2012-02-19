package com.gtug.shaircard.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Text;
import com.google.gson.Gson;
import com.gtug.shaircard.model.EMFService;
import com.gtug.shaircard.model.Event;
import com.gtug.shaircard.model.VCard;
import com.gtug.shaircard.model.VCardImage;

public class AddVCard extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/json");
		String body = Util.getPostBody(req);
		Gson gson = new Gson();
		VCard e = gson.fromJson(body, VCard.class);

		com.google.appengine.api.datastore.Text image = e.getBase64Image();
		VCardImage vci = new VCardImage();

		new org.apache.geronimo.mail.util.Base64();
		vci.setImage(new Blob(org.apache.geronimo.mail.util.Base64
				.decode(image.getValue())));

		EntityManager em = EMFService.get().createEntityManager();
		e.setBase64Image(null);
		em.persist(e);
		em.close();

		System.out.println(e.toJson());

		em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		vci.setVcardId(e.getId());
		em.persist(vci);
		em.getTransaction().commit();
		em.close();

		em = EMFService.get().createEntityManager();
		em.getTransaction().begin();
		Event event = em.find(Event.class, e.getEventId());
		event.incPeopleCount();
		em.getTransaction().commit();
		em.close();

		resp.getWriter().println("SUCCESS");
	}

}
