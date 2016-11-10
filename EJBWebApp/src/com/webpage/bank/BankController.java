package com.webpage.bank;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.webpage.entity.BankAccount;
import com.webpage.entity.Owner;
import com.webpage.entity.PhoneNumber;
import com.webpage.entity.SavingsAccount;

@ManagedBean
@SessionScoped
public class BankController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private TellerLocal teller;
	
	private BankAccount account;
	
	private int idToModify;
	private double amount;
	
	private Owner owner;
	
	private PhoneNumber phone;
	
	public BankController() {
		super();
		// Begin changed...
		clear();
		// End changed...
	}

	// Begin new...
	private void clear() {
		account = new BankAccount();
		owner = new Owner();
		account.setOwner(owner);
		phone = new PhoneNumber();
		owner.addNumber(phone);
	}
	// End new...
	
	public boolean isSavingsAccount() {
		return account instanceof SavingsAccount;
	}
	
	public void setSavingsAccount(boolean isSavings) {
		BankAccount newAccount;
		if (isSavings) {
			newAccount = new SavingsAccount();
		} else {
			newAccount = new BankAccount();
		}
		copyAccountProperties(newAccount, account);
		account = newAccount;
	}

	public String getAccountType() {
		return account.getClass().getSimpleName();
	}
	
	private void copyAccountProperties(BankAccount newAccount,
			BankAccount oldAccount) {
		newAccount.setOwner(oldAccount.getOwner());
		newAccount.setBalance(oldAccount.getBalance());
	}

	// Begin new...
	public String clearAccountAndGoToCreate() {
		clear();
		return "createAccountJPA";
	}
	// End new...
	
	public String createAccount() {
		// Need to create a new owner and account , otherwise new account gets linked to preexisting owner
		teller.createAccount(account);
		
		return accountDetailsRedirect();
	}
	
	public String deposit() {
		account = teller.deposit(idToModify, amount);
		
		return accountDetailsRedirect();
	}
	
	public String withdraw() {
		account = teller.withdraw(idToModify, amount);
		
		return accountDetailsRedirect();
	}
	
	public String closeAccount() {
		teller.closeAccount(idToModify);
		
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		external.getFlash().put("closedMessage", "Account #" + idToModify + " is closed.");
		
		return "accountClosed?faces-redirect=true";
	}


	public void loadAccountDetails() {
		account = teller.findById(account.getId());
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

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}
}
