package com.webpage.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Owner
 *
 */
@Entity
@Table(name="Owners")

public class Owner implements Serializable {

	   
	@Id
	@Column(name="OWNERID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private static final long serialVersionUID = 1L;
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="owner", fetch=FetchType.EAGER)
	private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

	public Owner() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void addNumber(PhoneNumber number) {
		this.phoneNumbers.add(number);
		number.setOwner(this);
	}
	
	public List<PhoneNumber> getPhoneNumbers() {
		return(Collections.unmodifiableList(phoneNumbers));
	}
   
}
