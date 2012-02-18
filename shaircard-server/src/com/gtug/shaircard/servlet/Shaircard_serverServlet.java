package com.gtug.shaircard.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gtug.shaircard.model.VCard;

@SuppressWarnings("serial")
public class Shaircard_serverServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/json");
		resp.getWriter().println(VCard.getSampleCard().toJson());
//		resp.getWriter().println("WHAT THE FUCK");
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doPost(req, resp);
//	}

}
