package com.webpage.bank;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.ibm.ws.sib.processor.impl.interfaces.ExternalConsumerLock;
import com.webpage.entity.BankAccount;

@Named
@RequestScoped
public class BankController {
	@EJB
	private TellerLocal teller;
	
	private BankAccount account = new BankAccount();
	
	private int idToModify;
	private double amount;
	
	public String createAccount() {
		teller.createAccount(account);
		
		return accountDetailsRedirect();
	}

	public void loadAccountDetails() {
		account = teller.findById(account.getId());
	}
	
	public String deposit() {
		account = teller.deposit(idToModify, amount);
		
		return(accountDetailsRedirect());
	}
	
	public String withdraw() {
		account = teller.withdraw(idToModify, amount);
		
		return(accountDetailsRedirect());
	}
	
	public String closeAccount() {
		teller.closeAccount(idToModify);
		
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		external.getFlash().put("closedMessage", "Account #" + idToModify + " is closed.");
		
		return("accountClosed?faces-redirect=true");
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
	private String accountDetailsRedirect() {
		return "accountDetails?faces-redirect=true&includeViewParams=true";
	}

	public int getIdToModify() {
		return idToModify;
	}

	public void setIdToModify(int idToModify) {
		this.idToModify = idToModify;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
