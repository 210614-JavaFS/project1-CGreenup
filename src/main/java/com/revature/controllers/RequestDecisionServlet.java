package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.ReimbService;

public class RequestDecisionServlet extends HttpServlet {

	private ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log = LoggerFactory.getLogger(RequestDecisionServlet.class);
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
	
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		System.out.println("REQUEST DECISION body = " + body);
		
		JsonNode parser = objectMapper.readTree(body);
		
		int id = parser.path("id").asInt();
		String userIdentifier = parser.path("username").toString().replace("\"", "");
		String action = parser.path("action").toString().replace("\"", "");
		
		ReimbService.getReimbService();
		
		System.out.println(id + " " + action + " " + userIdentifier);
		System.out.println(ReimbService.decideRequest(id, userIdentifier, action));
	}
}
