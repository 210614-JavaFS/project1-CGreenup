package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRoles;


public class UserService {
	private static UserService service = null;	
	private static UserDAO userDAO = new UserDAOImpl();
	private static ReimbursementDAO reimbDAO = new ReimbursementDAOImpl();
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	//returns 0 if success
	//returns 1 if username has error
	//returns 2 if password has error
	public static int login(User user) {
		String originalPassword = User.encryptPassword(user.getPassword());
		
		//Get user from the Database
		user = userDAO.getUser(user.getUsername().toLowerCase());
		
		//If the user does not exist in the database, the user object will be null
		if (user == null) {
			return 203;
		}
		
		//Since the user does exist in the database:
		//Check if the password obtained from the database has the same encryption hash as the password that was inputted
		if(user.getPassword().equals(originalPassword)) {
			if(user.getUserType() == UserRoles.MANAGER) 
				return 200;
			return 201;
		}
			
		
		//If the password didn't match, return a password didn't match status code
		return 204;
	}

	public static User getUser(String userIdentifier) {
		return userDAO.getUser(userIdentifier);
	}
	
	public static List<Reimbursement> getUsersRequests(User user){
		log.info("Attempting to get " + user.getFirstName() + "'s submitted requests.");
		System.out.println("Attempting to get " + user.getFirstName() + "'s submitted requests.");
		return reimbDAO.getUsersRequests(user);
	}
	
	
	private UserService() {}
	public static UserService getUserService() {
		if (service == null) {
			service = new UserService();
			log.info("Created new UserService");
		}
		return service;
	}
	
	
	
	
	
}
