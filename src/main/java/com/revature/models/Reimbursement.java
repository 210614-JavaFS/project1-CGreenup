package com.revature.models;

import java.util.Date;

public class Reimbursement {
	private int id;
	private double amount;
	private Date dateSubmitted;
	private Date dateResolved;
	private String description;
	//Stretch goal - images for receipts
	private User author;
	private User resolver;
	private ReimbursementStatus status;
	private ReimbursementTypes type;
}
