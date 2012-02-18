package com.gtug.shaircard.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gtug.shaircard.model.EMFService;
import com.gtug.shaircard.model.Event;

public class GetClosestEvent extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String latStr = req.getParameter("latitude");
		String lonStr = req.getParameter("longitude");
		double lat = 0, lon = 0;
		if (latStr == null || lonStr == null) {
			resp.getWriter().println("FAILURE: no coordinates");
			return;
		}
		lat = Double.parseDouble(latStr);
		lon = Double.parseDouble(lonStr);

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Event e");
		List<Event> eList = q.getResultList();

		final double llat = lat;
		final double llon = lon;
		Collections.sort(eList, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				double dist1;
				if (o1.getLatitude() != null) {
					dist1 = Math.abs(o1.getLatitude() - llat
							+ o1.getLongitude() - llon);
				} else {
					return 1;
				}

				double dist2;
				if (o2.getLatitude() != null) {
					dist2 = Math.abs(o2.getLatitude() - llat
							+ o2.getLongitude() - llon);
				} else {
					return -1;
				}

				if (dist1 < dist2) {
					return -1;
				} else {
					return 1;
				}
			}
		});

		if (eList.isEmpty()) {
			resp.getWriter().println("{ id = -1 }");
		} else {
			Event event = eList.get(0);
			if ((Math.abs(event.getLatitude() - lat) < Util.CloseLatRange)
					&& (Math.abs(event.getLongitude() - lon) < lon)) {
				resp.getWriter().println(event.toJson());
			} else {
				resp.getWriter().println("{ id = -1 }");
			}

		}
	}

}
