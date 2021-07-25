package com.revature.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private UserTypes userType;
	private User() {
		super();
	}
	private User(int userId, String username, String password, String email, UserTypes userType) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = userType;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserTypes getUserType() {
		return userType;
	}
	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	//This method encrypts a given string into SHA-256 encrypted hex-code
	public static String encryptPassword(String input) {
		StringBuilder hexadecimalString = null;
		byte[] byteArray;
		
		try {
			//convert input string into a SHA encrypted byte array
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byteArray = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
			
			//Now convert that byte array into hexadecimal
			hexadecimalString = new StringBuilder(2 * byteArray.length);
			for(byte i : byteArray) {
				String hexValue = Integer.toHexString(0xff & i);
				
				//If the hex value is only a single digit, add a 0
				if(hexValue.length() == 1) 
					hexadecimalString.append('0');
				hexadecimalString.append(hexValue);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return hexadecimalString.toString();
	}
	
}
