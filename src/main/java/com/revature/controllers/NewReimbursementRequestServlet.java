package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementTypes;
import com.revature.models.User;
import com.revature.services.ReimbService;
import com.revature.services.UserService;

public class NewReimbursementRequestServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService.getUserService();
		User user = UserService.getUser(req.getParameter("username"));
		String description = req.getParameter("description").replace("+", " ");
		double amount = Double.parseDouble(req.getParameter("dollarAmount"));
		ReimbursementTypes type = Reimbursement.stringToType(req.getParameter("selectForm"));
		ReimbService.getReimbService();
		ReimbService.addReimbRequest(user, amount, type, description);
		
		req.setAttribute("username", user.getUsername());
		//req.setAttribute("inputPassword", user.getPassword());
		RequestDispatcher dispatcher = req.getRequestDispatcher("success");
		dispatcher.forward(req, resp);
	}
}
