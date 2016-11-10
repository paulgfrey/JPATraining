package com.webpage.entity;

import com.webpage.entity.BankAccount;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SavingsAccount
 *
 */
@Entity
@DiscriminatorValue ("SAVINGS")
public class SavingsAccount extends BankAccount implements Serializable {

	
	private double interestRate;
	private static final long serialVersionUID = 1L;

	public SavingsAccount() {
		super();
	}   
	public double getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
   
}
