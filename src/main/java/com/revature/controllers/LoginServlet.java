package com.revature.controllers;

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

import com.revature.models.User;
import com.revature.services.UserService;

public class LoginServlet extends HttpServlet{
	
	private static Logger log = LoggerFactory.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("Made it to doPost");
		System.out.println("made it to doPost");
		
		User user = new User();
		
		String userIdentifier = request.getParameter("inputName");
		String password = request.getParameter("inputPassword");
		
		user.setUsername(userIdentifier);
		user.setPassword(password);
				
		System.out.println("Username: " + userIdentifier);
		System.out.println("Password: " + password);
		
		UserService.getUserService();
		int loginStatus = UserService.login(user);
		
		RequestDispatcher reqDispatcher = null;
		PrintWriter pw = response.getWriter();
		
		if(loginStatus == 0) {
			log.info("Login success");
			System.out.println("Login Success!");
						
			HttpSession session = request.getSession();
			session.setAttribute("username", userIdentifier);
			
			reqDispatcher = request.getRequestDispatcher("success");
			reqDispatcher.forward(request, response);
			
		}
		//If the username has the error
		else if(loginStatus == 1) {
			log.error("ERROR: Username not found");
			System.out.println("ERROR: Username not found");
		}
		else if(loginStatus == 2){
			log.error("ERROR: Password does not match");
			System.out.println("ERROR: Password does not match");
		}
		else {
			log.error("ERROR: Something really bad has happened when attempting to log in.");
			System.out.println("ERROR: Something really bad has happened when attempting to log in.");
		}
		
	}
}
