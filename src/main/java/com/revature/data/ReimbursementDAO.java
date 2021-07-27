package com.revature.data;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbursementDAO {
	public boolean submitRequest(Reimbursement form);
	public List<Reimbursement> getUsersRequests(User user);
}
