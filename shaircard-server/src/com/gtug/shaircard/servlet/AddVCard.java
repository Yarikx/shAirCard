package com.gtug.shaircard.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.tools.util.Logging;
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
		final Logger log = Logger.getLogger(AddVCard.class.getName());
		log.severe("Json body: " + body);
		VCard e = gson.fromJson(body, VCard.class);
		
		EntityManager em = EMFService.get().createEntityManager();
		EntityManager em2 = EMFService.get().createEntityManager();
		Query getPossibleCard = em
				.createQuery("SELECT c FROM VCard c WHERE c.eventId = :eventid AND c.creatorId = :creatorid");
		getPossibleCard.setParameter("eventid", e.getEventId());
		getPossibleCard.setParameter("creatorid", e.getCreatorId());

		VCardImage vci;

		boolean reuse = false;

		List<VCard> dbCard = getPossibleCard.getResultList();
		if (!dbCard.isEmpty()) {
			// Card already exists
			reuse = true;

			VCard v = dbCard.get(0);
			VCard.copyData(e, v);
			e = v;

			Query getPossibleImage = em2
					.createQuery("SELECT i FROM VCardImage i WHERE i.vcardId = :id");
			getPossibleImage.setParameter("id", v.getId());
			List<VCardImage> vciList = getPossibleImage.getResultList();

			if (!vciList.isEmpty()) {
				vci = vciList.get(0);
			} else {
				vci = new VCardImage();
			}

			// Try fetch VCardImage

		} else {
			vci = new VCardImage();
		}

		com.google.appengine.api.datastore.Text image = e.getBase64Image();

		if (image != null) {
			new org.apache.geronimo.mail.util.Base64();
			vci.setImage(new Blob(org.apache.geronimo.mail.util.Base64
					.decode(image.getValue())));
		} else {
			vci.setImage(null);
		}

		em.getTransaction().begin();
		e.setBase64Image(null);
		em.persist(e);
		em.getTransaction().commit();
		em.close();

		em2.getTransaction().begin();
		vci.setVcardId(e.getId());
		em2.persist(vci);
		em2.getTransaction().commit();
		em2.close();

		if (!reuse) {
			em = EMFService.get().createEntityManager();
			em.getTransaction().begin();
			Event event = em.find(Event.class, e.getEventId());
			event.incPeopleCount();
			em.getTransaction().commit();
			em.close();
		}

		resp.getWriter().println("SUCCESS");
	}

}
