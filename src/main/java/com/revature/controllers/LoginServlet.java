package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class LoginServlet extends HttpServlet{
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log = LoggerFactory.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
				
		log.info("Made it to doPost");
		System.out.println("made it to doPost");
		
		User user = new User();
		BufferedReader reader = request.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
	
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);		
		user = objectMapper.readValue(body, User.class);
		
		//DEBUG
		System.out.println(user.toString());
		
		UserService.getUserService();
		
		//200 User info correct and user is Finance Manager
		//201 user info correct and user is Employee
		//203 Username does not exist in database
		//204 Password does not match username/general error
		int loginStatus = UserService.login(user);
		response.setStatus(loginStatus);
		
		if (loginStatus == 200 || loginStatus == 201) {
			UserService.getUserService();
			
			//We need the rest of the user's info, as well as ensure that
			//the email is not in the username spot
			//So we call the service to fix and fill all fields
			user = UserService.getUser(user.getUsername());
			
			String json = objectMapper.writeValueAsString(user);
			response.getWriter().print(json);
		}
		
		//DEBUG
		System.out.println(loginStatus);		
	}
}
