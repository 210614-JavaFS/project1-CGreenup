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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		if(session != null) {
			new LogoutServlet().doGet(request, response);
		}else {

			
			pw.print("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" "
					+ "rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" "
					+ "crossorigin=\"anonymous\">");
			
			
			pw.print("<div class = \"alert alert-danger\">"
					+ "<strong>PLEASE RETURN TO HOME PAGE</strong>"
					+ "</div>");
			ToHome.execute(response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("application/json");
				
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
		
		UserService.getUserService();
		int loginStatus = UserService.login(user);
		
		System.out.println(loginStatus);
		
		switch(loginStatus) {
		case 0:
			response.setStatus(201);
			break;
		case 1:
			response.setStatus(203);
			break;
		case 2:
			response.setStatus(204);
			break;
		default:
			response.setStatus(405);
			break;
		}
//		if(loginStatus == 0) {
//			log.info("Login success");
//			System.out.println("Login Success!");
//						
//			session.setAttribute("username", userIdentifier);
//			
//			reqDispatcher = request.getRequestDispatcher("success");
//			reqDispatcher.forward(request, response);
//			
//		}
//		//If the username has the error
//		else if(loginStatus == 1) {
//			log.error("ERROR: Username not found");
//			System.out.println("ERROR: Username not found");
//			session.setAttribute("problemArea", "username");
//			
//			reqDispatcher = request.getRequestDispatcher("/");
//			reqDispatcher.forward(request, response);
//		}
//		//Otherwise, if the password has the error
//		else if(loginStatus == 2){
//			log.error("ERROR: Password does not match");
//			System.out.println("ERROR: Password does not match");
//			session.setAttribute("problemArea", "password");
//		}
//		else {
//			log.error("ERROR: Something really bad has happened when attempting to log in.");
//			System.out.println("ERROR: Something really bad has happened when attempting to log in.");
//			session.setAttribute("problemArea", "usernamepassword");
//		}
		
	}
}
