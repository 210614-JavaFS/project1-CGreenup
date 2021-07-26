package com.revature.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class ToHome {

	public static void execute(HttpServletResponse response) throws IOException {
		System.out.println("redirecting to home");
		response.sendRedirect("/project1");
	}
	
}
