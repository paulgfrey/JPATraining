package com.webpage.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: BankAccount
 *
 */
@Entity
@Table(name="BANKACCOUNT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACTTYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ACCOUNT")

public class BankAccount implements Serializable {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="BALANCE")
	private double balance;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="OWNER_ID")
	private Owner owner;
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

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

	private static final long serialVersionUID = 1L;

	public BankAccount() {
		super();
	}
   
}
