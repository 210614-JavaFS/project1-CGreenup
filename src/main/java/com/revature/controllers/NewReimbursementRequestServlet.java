package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementTypes;
import com.revature.models.User;
import com.revature.services.ReimbService;
import com.revature.services.UserService;

public class NewReimbursementRequestServlet extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(NewReimbursementRequestServlet.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		UserService.getUserService();
//		User user = UserService.getUser(request.getParameter("username"));
//		String description = request.getParameter("description").replace("+", " ");
//		double amount = Double.parseDouble(request.getParameter("dollarAmount"));
//		ReimbursementTypes type = Reimbursement.stringToType(request.getParameter("selectForm"));
		
		log.info("made it to New Reimb doPost");
		System.out.println("made it to New Reimb doPost");
		
		response.setContentType("application/json");
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
	
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		System.out.println(body);
		
		JsonNode parser = objectMapper.readTree(body);
		
		UserService.getUserService();
		User user = UserService.getUser(parser.path("author").asText());
		
		String description = parser.path("description").asText();
		
		double amount = parser.path("amount").asDouble();
		
		//Get the type as a string from the JSON, then
		//convert it into a string so that I can
		//convert it into a type
		ReimbursementTypes type = Reimbursement.stringToType(parser.path("type").toString());
		
		ReimbService.getReimbService();
		ReimbService.addReimbRequest(user, amount, type, description);
	}
}
 