package com.webpage.bank;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.webpage.entity.Owner;
import com.webpage.entity.PhoneNumber;

@Named
@RequestScoped
public class OwnerController {
	@Inject
	private TellerLocal teller;
	
	private int ownerIdToDisplay;
	private Owner owner;
	
	private PhoneNumber phoneToAdd = new PhoneNumber();
	
	public void loadOwnerDetails() {
		owner = teller.findOwnerById(ownerIdToDisplay);
	}
	
	public String displayOwner() {
		return ownerDetailsRedirect();
	}
	
	private String ownerDetailsRedirect() {
		return "ownerDetails?faces-redirect=true&includeViewParams=true";
	}

	public int getOwnerIdToDisplay() {
		return ownerIdToDisplay;
	}

	public void setOwnerIdToDisplay(int idToModify) {
		this.ownerIdToDisplay = idToModify;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public PhoneNumber getPhoneToAdd() {
		return phoneToAdd;
	}

	public void setPhoneToAdd(PhoneNumber phoneToAdd) {
		this.phoneToAdd = phoneToAdd;
	}

	public String addPhone() {
		teller.addNumber(ownerIdToDisplay, phoneToAdd);
		return ownerDetailsRedirect();
	}

}
