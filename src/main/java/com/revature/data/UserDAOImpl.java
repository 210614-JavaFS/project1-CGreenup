package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.ConnectionUtil;



public class UserDAOImpl implements UserDAO {
	Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public User getUser(String identifier) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql;
			if(identifier.contains("@")) {
				sql = "SELECT * FROM ERS_USERS EU INNER JOIN ERS_USER_ROLES EUR ON EUR.ERS_USER_ROLES_ID = EU.USER_ROLE_ID WHERE EU.USER_EMAIL = ?;";
			}else {
				sql = "SELECT * FROM ERS_USERS EU INNER JOIN ERS_USER_ROLES EUR ON EUR.ERS_USER_ROLES_ID = EU.USER_ROLE_ID WHERE EU.ERS_USERNAME = ?;";
			}
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//This is where SQL injection is checked for.
			statement.setString(1, identifier.toLowerCase());
			
			ResultSet result = statement.executeQuery();
			
			User user = new User();
			
			//ResultSets have a cursor; like how a Scanner has a cursor
			while(result.next()) {
				user.setFirstName(result.getString("USER_FIRST_NAME"));
				user.setLastName(result.getString("USER_LAST_NAME"));
				user.setUserId(result.getInt("ERS_USER_ID"));
				user.setUsername(result.getString("ERS_USERNAME"));
				user.setPassword(result.getString("ERS_PASSWORD"));
				user.setEmail(result.getString("USER_EMAIL"));
				
				String userLevel = result.getString("USER_ROLE");
				switch(userLevel) {
				case "MANAGER":
					user.setUserType(UserRoles.MANAGER);
					break;
				case "EMPLOYEE":
				default:
					user.setUserType(UserRoles.EMPLOYEE);
					break;
				}
			
				return user;
			}
			
		}catch(SQLException e) {
			log.error("Tried to find profile " + identifier +", ran into SQLException.");
			e.printStackTrace();
		}
		return null;
	}
	

}
