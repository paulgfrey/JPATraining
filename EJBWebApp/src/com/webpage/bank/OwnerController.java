package com.webpage.bank;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.webpage.entity.Owner;

@Named
@RequestScoped
public class OwnerController {
	@Inject
	private TellerLocal teller;
	
	private int ownerIdToDisplay;
	private Owner owner;
	
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

	
	

}
