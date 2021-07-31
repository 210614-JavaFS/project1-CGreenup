package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class RequestsServlet extends HttpServlet {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log = LoggerFactory.getLogger(RequestsServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Made it to RequestServlet - doGet()");
		System.out.println("Made it to Request doGet()");
		
		response.setContentType("application/json");
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
	
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		System.out.println("body = " + body);
		User user = objectMapper.readValue(body, User.class);
		System.out.println(user.toString());
		
		if(user != null) {
			UserService.getUserService();
			String json = objectMapper.writeValueAsString(UserService.getUsersRequests(user));
			response.getWriter().write(json);
			response.setStatus(200);
		}
	}
}
