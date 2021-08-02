package com.revature.data;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;

public interface ReimbursementDAO {
	public boolean submitRequest(Reimbursement form);
	public boolean updateRequest(Reimbursement form);
	public List<Reimbursement> getUsersRequests(User user);
	public List<Reimbursement> getReimbursementsOfStatus(ReimbursementStatus status);
}
