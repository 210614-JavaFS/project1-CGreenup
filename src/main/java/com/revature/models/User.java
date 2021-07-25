package com.revature.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private UserRoles userType;
	private String firstName;
	private String lastName;
	
	
	public User() {
		super();
	}

	public User(int userId, String username, String password, String email, UserRoles userType, String firstName,
			String lastName) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getUserId() 			{ return userId; 	}
	public String getUsername() 	{ return username;	}
	public String getPassword() 	{ return password;	}
	public String getEmail() 		{ return email;		}
	public UserRoles getUserType() 	{ return userType;	}
	public String getFirstName() 	{ return firstName;	}
	public String getLastName() 	{ return lastName;	}

	public void setUserId(int userId) 				{ this.userId = userId;			}
	public void setUsername(String username) 		{ this.username = username;		}
	public void setPassword(String password) 		{ this.password = password;		}
	public void setEmail(String email) 				{ this.email = email;			}
	public void setUserType(UserRoles userType) 	{ this.userType = userType;		}
	public void setFirstName(String firstName) 		{ this.firstName = firstName;	}
	public void setLastName(String lastName) 		{ this.lastName = lastName;		}
	
	

	@Override
	public String toString() {
		return "User [ userId = " + userId + ", username = " + username + ", password = " + password + ", email=" + email
				+ ", userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName + " ]";
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
				//Hex value of a byte
				String hexValue = Integer.toHexString(0xff & i);
				
				//If the hex value is only a single digit, add a 0
				if(hexValue.length() == 1) 
					hexadecimalString.append('0');
				
				//Add the hex value to the entire string of hex values
				hexadecimalString.append(hexValue);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hexadecimalString.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (userType != other.userType)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		result = prime * result + userId;
//		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
//		result = prime * result + ((username == null) ? 0 : username.hashCode());
//		return result;
//	}
	
}
