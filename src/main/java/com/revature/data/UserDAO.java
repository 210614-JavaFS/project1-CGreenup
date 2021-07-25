package com.revature.data;

import com.revature.models.User;

public interface UserDAO {
	public User getUser(String identifier);
}
