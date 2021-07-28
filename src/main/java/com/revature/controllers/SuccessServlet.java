package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.services.UserService;

public class SuccessServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		if(session != null) {
			String userIdentifier = request.getParameter("username");
			session.setAttribute("username", userIdentifier);
			
			doPost(request, response);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			ToHome.execute(resp);
		} else {
			//DEBUG
			UserService.getUserService();
			User user = UserService.getUser((String) session.getAttribute("username"));

			if(user.getUserType() == UserRoles.MANAGER) {
				pw.print("<div><p>Manager</p>");
				pw.print("<p>Second ptag</p></div>");
			}else {
				displayEmployeeScreen(user, pw);
			}
			
		}
	}
	
	protected void displayEmployeeScreen(User user, PrintWriter pw) {
		setEmployeeStylesheet(pw);
		
		addChangeViewScript(pw);
		
		displayEmployeeHTML(user, pw);
	}

	private void setEmployeeStylesheet(PrintWriter pw) {
		pw.print("<style>"
				+ "#mainDiv{"
				+ "  margin: 20px;"
				+ "}"
				+ ""
				+ "#newReq{"
				+ "  width:100%;"
				+ "  height:70px;"
				+ "}"
				+ ""
				+ "#tableHead{"
				+ "  background: black;"
				+ "  color: white;"
				+ "}"
				+ ""
				+ "h5{"
				+ "  padding-top:10px;"
				+ "  padding-bottom: 5px;"
				+ "  padding-left: 8px;"
				+ "}"
				+ "</style>");
	}

	private void displayEmployeeHTML(User user, PrintWriter pw) {
		pw.print("<div align='right' style='margin-right:10px; margin-top:10px;'>"
				+ "  <form action = 'logout'>"
				+ "    <button class=\"btn btn-primary\" id='logoutBtn'>Sign out</button>"
				+ "  </form>"
				+ "</div>"
				+ "<div id='mainDiv'>"
				+ "  <h2>Welcome, " + user.getFirstName() + "</h2>"
				+ "    <button id='newReq' class = \"btn btn-outline-secondary\" onclick=clearAll(" + user.getUsername() +")> Create New Request </button>"
				+ "  <div id='tableDiv'>"
				+ "    <h5>Submitted Requests</h5>"
				+ "  <table class=\"table\" id='requestTable'>"
				+ "    <thead id='tableHead'>"
				+ "      <tr>"
				+ "        <th scope='col'> ID </th>"
				+ "        <th scope='col'> Description </th>"
				+ "        <th scope='col'> Amount </th>"
				+ "        <th scope='col'> Status </th>"
				+ "      </tr>"
				+ "    </thead>"
				+ "    "
				+ "    <tbody id='tableBody'>"
				+ 		getRequests(user)
				+ "    </tbody>"
				+ "  </table>"
				+ "  </div>"
				+ "</div>");
	}

	private void addChangeViewScript(PrintWriter pw) {
		pw.print("<script src='C:\\Users\\cgree\\Documents\\workspace-spring-tool-suite-4-4.8.1.RELEASE\\project1-CGreenup\\src\\main\\webapp\\printSubmissionForm.js'></script>");
	}
	
	private String getRequests(User user) {
		UserService.getUserService();
		List<Reimbursement> usersRequests = UserService.getUsersRequests(user);
		StringBuilder bodyContent = new StringBuilder();
		
		for(Reimbursement r : usersRequests) {
			bodyContent.append("<tr>");
				bodyContent.append("<td>" + r.getId() + "</td>");
				bodyContent.append("<td>" + r.getType() + ": " + r.getDescription() + "</td>");
				bodyContent.append("<td>$" + r.getAmount() + "</td>");
				
				if (r.getStatus() == ReimbursementStatus.APPROVED)
					bodyContent.append("<td class='bg-success'>");
				else if(r.getStatus() == ReimbursementStatus.DENIED)
					bodyContent.append("<td class='bg-danger'>");
				else
					bodyContent.append("<td class='bg-warning'>");
				
				bodyContent.append(r.getStatus() + "</td>");
			bodyContent.append("</tr>");
		}
		
		return bodyContent.toString();
	}
	
}
