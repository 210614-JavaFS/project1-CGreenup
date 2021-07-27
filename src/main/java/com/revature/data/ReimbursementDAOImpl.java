package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementTypes;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	Logger log = LoggerFactory.getLogger(ReimbursementDAOImpl.class);
	
	
	@Override
	public boolean submitRequest(Reimbursement form) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO ERS_REIMBURSEMENT "
					+ "(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) VALUES"
					+ "(?, ?, ?, ?, ?, ?, (select REIMB_STATUS_ID from ERS_REIMBURSEMENT_STATUS where REIMB_STATUS = ?), (select REIMB_TYPE_ID from ERS_REIMBURSEMENT_TYPE where REIMB_TYPE = ?));";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setDouble(++index, form.getAmount());
			statement.setTimestamp(++index, new Timestamp(new Date().getTime()));
			statement.setNull(++index, 0);
			statement.setString(++index, form.getDescription());
			statement.setInt(++index, form.getAuthor().getUserId());
			statement.setNull(++index, 0);
			statement.setString(++index, ReimbursementStatus.PENDING.toString());
			statement.setString(++index, form.getType().toString());
			
			
			log.info(statement.toString());
			System.out.println(statement.toString());
			
			statement.execute();
			return true;
			
		}catch(SQLException e) {
			log.error("SQLException in submitRequest, ReimbursementDAOImpl");
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public List<Reimbursement> getUsersRequests(User user) {
		List<Reimbursement> list = null;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			list = new ArrayList<Reimbursement>();
			String sql = "SELECT "
					+ "r.REIMB_ID, r.REIMB_AMOUNT, t.REIMB_TYPE, r.REIMB_DESCRIPTION, s.REIMB_STATUS "
					+ "FROM 		ERS_REIMBURSEMENT r "
					+ "INNER JOIN 	ERS_REIMBURSEMENT_TYPE t 	ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID "
					+ "INNER JOIN 	ERS_REIMBURSEMENT_STATUS s 	ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID "
					+ "WHERE r.REIMB_AUTHOR = ? "
					+ "ORDER BY r.REIMB_SUBMITTED DESC;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, user.getUserId());
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setId(result.getInt("REIMB_ID"));
				reimb.setAmount(result.getDouble("REIMB_AMOUNT"));
				reimb.setDescription(result.getString("REIMB_DESCRIPTION"));
				
				switch(result.getString("REIMB_TYPE")) {
				case "MOVING":
					reimb.setType(ReimbursementTypes.MOVING);
					break;
				case "PARKING":
					reimb.setType(ReimbursementTypes.PARKING);
					break;
				case "COMMUTE":
					reimb.setType(ReimbursementTypes.COMMUTE);
					break;
				case "BUSINESS":
					reimb.setType(ReimbursementTypes.BUSINESS);
					break;
				default:
					reimb.setType(ReimbursementTypes.OTHER);
				}
				
				switch(result.getString("REIMB_STATUS")) {
				default:
				case "PENDING":
					reimb.setStatus(ReimbursementStatus.PENDING);
					break;
				case "APPROVED":
					reimb.setStatus(ReimbursementStatus.APPROVED);
					break;
				case "DENIED":
					reimb.setStatus(ReimbursementStatus.DENIED);
					break;
				}
				
				list.add(reimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	
}
