package com.webpage.bank;

import java.util.List;

import com.webpage.entity.BankAccount;
import com.webpage.entity.Owner;
import com.webpage.entity.PhoneNumber;

public interface TellerLocal {
	public void createAccount(int id, String ownername, double balance);
	public int createAccount(BankAccount account);
	public BankAccount findById(int id);

	public BankAccount deposit(int id, double amount);
	public BankAccount withdraw(int id, double amount);
	public void closeAccount(int id);
	
	public List<BankAccount> listAllAccounts();
	public List<BankAccount> findWithBalance(double amount);
	
	public Owner findOwnerById(int ownerId);
	
	public Owner addNumber(int ownerId, PhoneNumber phoneNumber);
	
	public List<BankAccount> findAccountForAreaCode(int areaCode);
	public List<PhoneNumber> findNumbersForAmount(double amount);
}
