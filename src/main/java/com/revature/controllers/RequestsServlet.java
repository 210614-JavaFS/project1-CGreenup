package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementTypes;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.services.ReimbService;
import com.revature.services.UserService;

public class RequestsServlet extends HttpServlet {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log = LoggerFactory.getLogger(RequestsServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Made it to RequestServlet - doGet()");
		System.out.println("Made it to Request doPost()");
		
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
		
		JsonNode parser = objectMapper.readTree(body);
		
		User user = new User();
		
		user.setUsername(parser.path("username").toString().replace("\"", ""));
		user = UserService.getUser(user.getUsername());
		
		System.out.println(user.toString());
		UserService.getUserService();
		
		//If the user is an employee, return all of the requests for that employee
		if(user.getUserType() == UserRoles.EMPLOYEE) {
			UserService.getUserService();
			String json = objectMapper.writeValueAsString(UserService.getUsersRequests(user));
			response.getWriter().write(json);
			response.setStatus(200);
		}
		//If the user is a manager, return the requests they ask for in the body
		else if (user.getUserType() == UserRoles.MANAGER) {
			ReimbService.getReimbService();
			ReimbursementStatus givenStatus = Reimbursement.stringToStatus(parser.path("status").toString().replace("\"", ""));
			String json = objectMapper.writeValueAsString(ReimbService.getReimbRequestsByStatus(givenStatus));
			response.getWriter().write(json);
			response.setStatus(200);
		}else {
			response.setStatus(400);
		}
	}
}
