package com.webpage.bank;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CreateJDBCController {
	@EJB
	private TellerLocal teller;
	
	private int id;
	private String ownerName;
	private double balance;
	private String message;
	
	public void createAccount() {
		teller.createAccount(id, ownerName, balance);
		message = "Account Created!";
		id = 0;
		ownerName = "";
		balance = 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getMessage() {
		return message;
	}

}
