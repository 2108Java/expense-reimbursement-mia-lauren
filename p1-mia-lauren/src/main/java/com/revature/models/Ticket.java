package com.revature.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Ticket {
	
	private final String STATUS[] = {"PENDING", "APPROVED", "REJECTED"};
	private final String EXPENSE[] = {"LODGING", "TRAVEL", "FOOD", "OTHER"};
	
	private int id;
	private String username;
	private String expenseType;
	private double amount;
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //tell jackson how to format the date
	private Date submittedOn;
	private String status;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(String username, String expenseType, double amount, String description, String status) {
		super();
		this.username = username;
		setExpenseType(expenseType);
		this.amount = amount;
		this.description = description;
		setStatus(status);
	}

	public Ticket(int id, String username, String expenseType, double amount, String description, Date submittedOn,
			String status) {
		super();
		this.id = id;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
