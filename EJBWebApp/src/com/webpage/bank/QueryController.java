package com.webpage.bank;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.webpage.entity.BankAccount;
import com.webpage.entity.PhoneNumber;

@Named
@RequestScoped
public class QueryController {
	@Inject
	private TellerLocal teller;
	
	private List<BankAccount> accountResults = new ArrayList<BankAccount>();
	private List<PhoneNumber> phoneResults = new ArrayList<PhoneNumber>();
	
	private int areaCode;
	private double amount;
	
	public void areaCodeQuery() {
		// insert code here for query
		accountResults = teller.findAccountForAreaCode(areaCode);
		
		amount = 0;
	}
	
	public void phoneQuery() {
		// insert code here for query
		phoneResults = teller.findNumbersForAmount(amount);

		areaCode = 0;
	}
	
	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int idToModify) {
		this.areaCode = idToModify;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<BankAccount> getAccountResults() {
		return accountResults;
	}

	public List<PhoneNumber> getPhoneResults() {
		return phoneResults;
	}

	
	

}
