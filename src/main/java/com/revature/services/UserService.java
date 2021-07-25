package com.revature.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
import com.revature.models.User;


public class UserService {
	private static UserService service = null;	
	private static UserDAO userDAO = new UserDAOImpl();
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	//returns 0 if success
	//returns 1 if username has error
	//returns 2 if password has error
	public static int login(User user) {
		String originalPassword = User.encryptPassword(user.getPassword());
		
		//Get user from the Database
		user = userDAO.getUser(user.getUsername());
		
		//If the user does not exist in the database, the user object will be null
		if (user == null) {
			return 1;
		}
		
		//Since the user does exist in the database:
		//Check if the password obtained from the database has the same encryption hash as the password that was inputted
		if(user.getPassword().equals(originalPassword))
			return 0;
		
		//If the password didn't match, return a password didn't match status code
		return 2;
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
