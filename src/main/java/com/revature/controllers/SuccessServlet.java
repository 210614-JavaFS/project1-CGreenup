package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.services.UserService;

public class SuccessServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		pw.print("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" "
				+ "rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" "
				+ "crossorigin=\"anonymous\">"
				+ "<link rel=\"stylesheet\" href=\"employee.css\">");
		
		if(session == null) {
			pw.print("<div class = \"alert alert-danger\">"
					+ "<strong>PLEASE RETURN TO HOME PAGE</strong>"
					+ "</div>");
		} else {
			//DEBUG
			UserService.getUserService();
			User user = UserService.getUser((String) session.getAttribute("username"));

			if(user.getUserType() == UserRoles.MANAGER) {
				pw.print("<div><p>Manager</p>");
				pw.print("<p>Second ptag</p></div>");
			}else {
				displayEmployeeScreen(pw);
			}
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void displayEmployeeScreen(PrintWriter pw) {
		pw.print("<style>"
				+ "#mainDiv{\r\n"
				+ "    margin: 20px;\r\n"
				+ "  }\r\n"
				+ "  \r\n"
				+ "  button{\r\n"
				+ "    width:100%;\r\n"
				+ "    height:70px;\r\n"
				+ "  }\r\n"
				+ "  \r\n"
				+ "  #tableHead{\r\n"
				+ "    background: black;\r\n"
				+ "    color: white;\r\n"
				+ "  }\r\n"
				+ "  \r\n"
				+ "  label{\r\n"
				+ "    padding-bottom: 5px;\r\n"
				+ "    padding-left: 8px;\r\n"
				+ "  }"
				+ "</style>");
		
		pw.print("<div id='mainDiv'>\r\n"
				+ "  <button class = \"btn btn-outline-secondary\" >Create New Request</button>\r\n"
				+ "  \r\n"
				+ "  <div id='tableDiv'>\r\n"
				+ "    <label for='requestTable'><b>Submitted Requests</b></label>\r\n"
				+ "  <table class=\"table\" id='requestTable'>\r\n"
				+ "    <thead id='tableHead'>\r\n"
				+ "      <tr>\r\n"
				+ "        <th scope='col'> ID </th>\r\n"
				+ "        <th scope='col'> Description </th>\r\n"
				+ "        <th scope='col'> Amount </th>\r\n"
				+ "        <th scope='col'> Status </th>\r\n"
				+ "      </tr>\r\n"
				+ "    </thead>\r\n"
				+ "    \r\n"
				+ "    <tbody>\r\n"
				+ "      \r\n"
				+ "    </tbody>\r\n"
				+ "  </table>\r\n"
				+ "  </div>\r\n"
				+ "</div>");
	}
	
}
