package com.gtug.shaircard.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
		String latStr = req.getParameter("latitude");
		String lonStr = req.getParameter("longitude");
		String onlyNowStr = req.getParameter("onlyNow");
		String pageStr = req.getParameter("page");
		double lat = 0, lon = 0;
		int page = 0, from = 0, to = 29;
		boolean onlyNow = true;
		if (requestText == null) {
			requestText = "";
		}
		if (latStr != null) {
			lat = Double.parseDouble(latStr);
		}
		if (lonStr != null) {
			lon = Double.parseDouble(lonStr);
		}
		if (onlyNowStr != null) {
			onlyNow = Boolean.parseBoolean(onlyNowStr);
		}
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		if (page != 0) {
			from = 30 + page * 10;
			to = from + 9;
		}
		String[] keywords = requestText.split(",");

		EntityManager em = EMFService.get().createEntityManager();
		String sqlReq;
		Date now = new Date();
		if (onlyNow) {
			sqlReq = "SELECT e FROM Event e WHERE e.timeEnd > :now";
		} else {
			sqlReq = "SELECT e FROM Event e";
		}
		Query q = em.createQuery(sqlReq);
		q.setParameter("now", now);
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

		List<Event> fList = new ArrayList<Event>();
		for (Event e : eList) {
			for (String s : keywords) {
				String ss = ".*" + s.toLowerCase() + ".*";
				if ((e.getName() != null && e.getName().toLowerCase()
						.matches(ss))
						|| (e.getDescription() != null && e.getDescription()
								.toLowerCase().matches(ss))
						|| ((e.getAddress() != null && e.getAddress()
								.toLowerCase().matches(ss)))) {
					fList.add(e);
					break;
				}
			}
		}

		from = Math.min(from, fList.size());
		to = Math.min(to, fList.size());
		
		resp.getWriter().println(Event.listToJson(fList.subList(from, to)));
	}

}
