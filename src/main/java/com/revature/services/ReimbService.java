package com.revature.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementTypes;
import com.revature.models.User;

public class ReimbService {
	private static ReimbService service;
	private static Logger log = LoggerFactory.getLogger(ReimbService.class);
	private static ReimbursementDAO dao = new ReimbursementDAOImpl();
	
	public static boolean addReimbRequest(User user, double amount, ReimbursementTypes type, String description) {
		Reimbursement form = new Reimbursement();
		form.setAmount(amount);
		form.setAuthor(user);
		form.setDescription(description);
		form.setType(type);
		
		return dao.submitRequest(form);
	}
	
	private ReimbService() {}
	public static ReimbService getReimbService() {
		if (service == null) {
			service = new ReimbService();
			log.info("New ReimbService created");
		}
		return service;
	}
}
