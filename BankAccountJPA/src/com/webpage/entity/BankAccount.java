package com.webpage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: BankAccount
 *
 */
@Entity
@Table(name="BANKACCOUNT")

public class BankAccount implements Serializable {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="BALANCE")
	private double balance;
	
	@Column(name="OWNERNAME")
	private String ownername;
	
	public void deposit(double amount) {
		this.setBalance(getBalance() + amount);
	}
	
	public void withdraw(double amount) {
		this.setBalance(getBalance() - amount);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	private static final long serialVersionUID = 1L;

	public BankAccount() {
		super();
	}
   
}
