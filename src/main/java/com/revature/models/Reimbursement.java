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
	private String name;
	
	private User resolver;
	private ReimbursementStatus status;
	private ReimbursementTypes type;
	
	public Reimbursement(int id, double amount, Date dateSubmitted, Date dateResolved, String description, User author,
			User resolver, ReimbursementStatus status, ReimbursementTypes type, String name) {
		this.id = id;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.name = name;
	}

	public Reimbursement() {
		super();
	}

	public int getId() 						{ return id;			}
	public double getAmount() 				{ return amount;		}
	public Date getDateSubmitted() 			{ return dateSubmitted;	}
	public Date getDateResolved() 			{ return dateResolved;	}
	public String getDescription() 			{ return description;	}
	public User getAuthor() 				{ return author;		}
	public User getResolver() 				{ return resolver;		}
	public ReimbursementStatus getStatus() 	{ return status;		}
	public ReimbursementTypes getType() 	{ return type;			}
	public String getName()					{ return name;			}

	public void setId(int id) 								{ this.id = id;								}
	public void setAmount(double amount) 					{ this.amount = amount>0?amount:0;			}
	public void setDateSubmitted(Date dateSubmitted) 		{ this.dateSubmitted = dateSubmitted;		}
	public void setDateResolved(Date dateResolved) 			{ this.dateResolved = dateResolved;			}
	public void setDescription(String description) 			{ this.description = description;			}
	public void setAuthor(User author) 						{ this.author = author;						}
	public void setResolver(User resolver) 					{ this.resolver = resolver;					}
	public void setStatus(ReimbursementStatus status) 		{ this.status = status;						}
	public void setType(ReimbursementTypes type) 			{ this.type = type;							}
	public void setName(String name)						{ this.name = name;							}
	public void setName(String firstName, String lastName)	{ this.name = firstName + " " + lastName;	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", dateSubmitted=" + dateSubmitted + ", dateResolved="
				+ dateResolved + ", description=" + description + ", author=" + author + ", resolver=" + resolver
				+ ", status=" + status + ", type=" + type + "]";
	}
	
	public static ReimbursementTypes stringToType(String input) {
		switch(input.strip().toUpperCase().toString()) {
		case "MOVING":
			return ReimbursementTypes.MOVING;
		case "BUSINESS":
			return ReimbursementTypes.BUSINESS;
		case "COMMUTE":
			return ReimbursementTypes.COMMUTE;
		default :
			return ReimbursementTypes.OTHER;
		}
	}
	
	public static ReimbursementStatus stringToStatus(String input) {
		switch(input.strip().toUpperCase().toString()) {
		case "PENDING":
		default:
			return ReimbursementStatus.PENDING;
		case "DENIED":
			return ReimbursementStatus.DENIED;
		case "APPROVED":
			return ReimbursementStatus.APPROVED;
		}
	}
	
	
}
