package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
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

}
