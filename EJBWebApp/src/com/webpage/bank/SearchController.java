package com.webpage.bank;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.webpage.entity.BankAccount;

@Named
@RequestScoped
public class SearchController {
	@Inject
	private TellerLocal teller;
	
	private List<? extends BankAccount> results = new ArrayList<BankAccount>();
	
	private int idToSearch;
	private int queryAmount;
	private boolean sortResults;
	
	public String accountIDSearch() {
		
		return "accountDetails?faces-redirect=true&id=" + idToSearch;
	}
	
	public void listAllAccounts() {
		results = teller.listAllAccounts();
	}
	
	public void balanceSearch() {
		results = teller.findWithBalance(queryAmount, sortResults);
	}
	
	public int getIdToSearch() {
		return idToSearch;
	}

	public void setIdToSearch(int idToModify) {
		this.idToSearch = idToModify;
	}

	public int getQueryAmount() {
		return queryAmount;
	}

	public void setQueryAmount(int amount) {
		this.queryAmount = amount;
	}

	public List<? extends BankAccount> getResults() {
		return results;
	}

	public void listAllSavingsAccounts() {
		results = teller.listAllSavingsAccounts();
	}

	public boolean isSortResults() {
		return sortResults;
	}

	public void setSortResults(boolean sortResults) {
		this.sortResults = sortResults;
	}
	

}
