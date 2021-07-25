package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if(loginStatus == 0) {
			log.info("Login success");
			log.info(user.toString());
		}
		//If the username has the error
		else if(loginStatus == 1) {
			log.error("ERROR: Username not found");
			System.out.println("ERROR: Username not found");
		}
		else {
			log.error("ERROR: Password does not match");
			System.out.println("ERROR: Password does not match");
		}
		
	}
}
