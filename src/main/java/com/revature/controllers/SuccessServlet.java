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
				+ ""
				+ "#formDiv{\r\n"
				+ "  padding: 10px;\r\n"
				+ "  margin: 30px;\r\n"
				+ "  border-radius:10px;\r\n"
				+ "  background: darkgray;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "label{\r\n"
				+ "  padding\r\n"
				+ "}"
				+ "input{"
				+ " margin-top:5px;"
				+ "}"
				+ "</style>");
	}

	private void displayEmployeeHTML(User user, PrintWriter pw) {
		String HTMLl = "<script>"
				+ "let user;"
				+ "    //formDiv\r\n"
				+ "    let formDiv = document.createElement('div');\r\n"
				+ "    formDiv.id = 'formDiv'\r\n"
				+ "    \r\n"
				+ "    //Form\r\n"
				+ "    let reimbForm = document.createElement('form');\r\n"
				+ "    reimbForm.id = 'signUpForm';\r\n"
				+ "    reimbForm.action = 'new-request';\r\n"
				+ "    reimbForm.method = 'post';\r\n"
				+ "    \r\n"
				+ "    //Select Label\r\n"
				+ "    let label = document.createElement('label');\r\n"
				+ "    label.innerHTML = 'Reimbursement Type';\r\n"
				+ "    reimbForm.appendChild(label);\r\n"
				+ "    \r\n"
				+ "    //Select\r\n"
				+ "    let selectForm = document.createElement('select');\r\n"
				+ "    selectForm.id = 'selectForm';\r\n"
				+ "    selectForm.className = 'form-control';\r\n"
				+ "    \r\n"
				+ "    //options\r\n"
				+ "    let allOptions = ['Moving', 'Parking', 'Commute', 'Business', 'Other'];\r\n"
				+ "    for(let option of allOptions){\r\n"
				+ "        let opt = document.createElement('option');\r\n"
				+ "        opt.value = option.toUpperCase();\r\n"
				+ "        opt.innerHTML = option;\r\n"
				+ "        selectForm.appendChild(opt);\r\n"
				+ "    }\r\n"
				+ "    reimbForm.appendChild(selectForm);\r\n"
				+ "    \r\n"
				+ "    //Dollar Amount label\r\n"
				+ "    label = document.createElement('label');\r\n"
				+ "    label.innerHTML = \"Dollar amount for reimbursement\";\r\n"
				+ "    reimbForm.appendChild(label);\r\n"
				+ "    \r\n"
				+ "    //Dollar amount input\r\n"
				+ "    let input = document.createElement('input');\r\n"
				+ "    input.id = 'dollarAmount';\r\n"
				+ "    input.type = 'text';\r\n"
				+ "    input.name = 'dollarAmount';"
				+ "    input.className = 'form-control';\r\n"
				+ "    input.placeholder = '000.00';\r\n"
				+ "    input.required = 'true';\r\n"
				+ "    reimbForm.appendChild(input);\r\n"
				+ "    \r\n"
				+ "    //Description Label\r\n"
				+ "    label = document.createElement('label');\r\n"
				+ "    label.innerHTML = 'A short description for the request';\r\n"
				+ "    reimbForm.appendChild(label);\r\n"
				+ "    \r\n"
				+ "    //Description input\r\n"
				+ "    input = document.createElement('input');\r\n"
				+ "    input.type = 'text';\r\n"
				+ "    input.id = 'description';\r\n"
				+ "    input.name = 'description';"
				+ "    input.className = 'form-control';\r\n"
				+ "    input.placeholder = 'Description';\r\n"
				+ "    input.required = 'true';\r\n"
				+ "    reimbForm.appendChild(input);\r\n"
				+ "    \r\n"
				+ "    label = document.createElement('label');\r\n"
				+ "    reimbForm.appendChild(label);\r\n"
				+ "    \r\n"
				+ "    let button = document.createElement('button');\r\n"
				+ "    button.id = 'okButton';\r\n"
				+ "    button.className = 'btn form-control btn-secondary';\r\n"
				+ "    button.disabled = 'true';\r\n"
				+ "    button.innerHTML = 'Submit';\r\n"
				+ "	   button.type = 'submit';"
				+ "    reimbForm.appendChild(button);\r\n"
				+ "    \r\n"
				+ "    function showForm(){\r\n"
				+ "		   let mainDiv = document.getElementById('mainDiv');"
				+ "    input = document.createElement('input');\r\n"
				+ "    input.value = user;\r\n"
				+ "	   input.name = 'username';\r\n"
				+ "		input.type = 'hidden';"
				+ "    reimbForm.appendChild(input);"
				+ "	   formDiv.appendChild(reimbForm);"
				+ "        mainDiv.appendChild(formDiv);\r\n"
				+ "		    signUpForm = document.getElementById('signUpForm');"
				+ "		      dollarAmount = document.getElementById('dollarAmount');"
				+ "	   descriptionField = document.getElementById('description');"
				+ "	  okButton = document.getElementById('okButton');"
				
				+ "      descriptionField.addEventListener('keyup', validateForm);\r\n"
				+ "      dollarAmount.addEventListener('keyup', validateForm);\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "    function clearAll(username) {\r\n"
				+ "        var div = document.getElementById('mainDiv');\r\n"
				+ "        while(div.firstChild){\r\n"
				+ "            div.removeChild(div.firstChild);\r\n"
				+ "        }\r\n"
				+ "        setUser(username);\r\n"
				+ "        showForm();\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "    const inputRegex = /^\\d+(\\.\\d{1,2})?$/;\r\n"
				+ "      var signUpForm;"
				+ "      var dollarAmount;"
				+ "      var descriptionField;"
				+ "      var okButton;"
				+ "    \r\n"
				+ "      \r\n"
				+ "        function setUser(username){\r\n"
				+ "            user = username;\r\n"
				+ "        }\r\n"
				+ "    \r\n"
				+ "      function validateForm() {\r\n"
				+ "        descriptionNotEmpty = descriptionField.checkValidity();\r\n"
				+ "        dollarAmountIsValid = inputRegex.test(dollarAmount.value)\r\n"
				+ "    \r\n"
				+ "        if ( descriptionNotEmpty && dollarAmountIsValid) {\r\n"
				+ "          okButton.disabled = false;\r\n"
				+ "        } else {\r\n"
				+ "          okButton.disabled = true;\r\n"
				+ "        }\r\n"
				+ "      }</script>"
				+ ""
				+ "<div align='right' style='margin-right:10px; margin-top:10px;'>"
				+ "  <form action = 'logout'>"
				+ "    <button class=\"btn btn-primary\" id='logoutBtn'>Sign out</button>"
				+ "  </form>"
				+ "</div>"
				+ "<div id='mainDiv'>"
				+ "  <h2>Welcome, " + user.getFirstName() + "</h2>"
				+ "    <button id='newReq' class = \"btn btn-outline-secondary\" onclick=clearAll('" + user.getUsername() +"')> Create New Request </button>"
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
				+ "</div>";
		
		pw.print(HTMLl);
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
