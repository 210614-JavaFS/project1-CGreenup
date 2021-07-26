package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Logger log = LoggerFactory.getLogger(LogoutServlet.class);
		log.info("logging out");
		System.out.println("logging out");
		
		HttpSession session = req.getSession(false);
		
		if(session!=null) {
			session.invalidate();
		}
		
		ToHome.execute(resp);
		
	}
	
}
