package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.UserService;

public class LoginServlet extends HttpServlet{
	
	private static UserService userService = new UserService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("inputName");
		String password = request.getParameter("inputPassword");
		
		if(userService.login(username, password)) {
			
		}else {
			
		}
	}
}
