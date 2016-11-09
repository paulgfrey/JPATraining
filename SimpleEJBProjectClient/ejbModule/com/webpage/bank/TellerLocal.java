package com.webpage.bank;

import com.webpage.entity.BankAccount;

public interface TellerLocal {
	public void createAccount(int id, String ownername, double balance);
	public int createAccount(BankAccount account);
	public BankAccount findById(int id);

	public BankAccount deposit(int id, double amount);
	public BankAccount withdraw(int id, double amount);
	public void closeAccount(int id);
}
