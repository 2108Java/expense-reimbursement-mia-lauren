package com.revature.models;

import java.util.Date;

public class Ticket {
	
	private final String STATUS[] = {"Pending", "Approved", "Rejected"};
	private final String EXPENSE[] = {"Lodging", "Travel", "Food", "Other"};
	
	private int id;
	private int userId;
	private String expenseType;
	private double amount;
	private String description;
	private Date submittedOn;
	private String status;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(int userId, String expenseType, double amount, String description, String status) {
		super();
		this.userId = userId;
		setExpenseType(expenseType);
		this.amount = amount;
		this.description = description;
		setStatus(status);
	}

	public Ticket(int id, int userId, String expenseType, double amount, String description, Date submittedOn,
			String status) {
		super();
		this.id = id;
		this.userId = userId;
		setExpenseType(expenseType);
		this.amount = amount;
		this.description = description;
		this.submittedOn = submittedOn;
		setStatus(status);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		
		//ensure that the expense type is one of four predetermined options
		for(String s: EXPENSE) {
			if(s.equals(expenseType)) {
				this.expenseType = expenseType;
			}
		}
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		
		//ensure that the status is one of three predetermined options
		for(String s: STATUS) {
			if(s.equals(status)) {
				this.status = status;
			}
		}

	}
	

}
