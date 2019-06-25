package com.moo.rest.addressbook.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String firstName;
	private String secondName;


	private String emailAddress;
	
	@OneToMany(mappedBy="contact")
	private List<Address> addresses;
	
	public Contact(){
		super();
	}
	
	public Contact(Integer id, String firstName, String secondName, String emailAddress) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.emailAddress = emailAddress;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", emailAddress="
				+ emailAddress + "]";
	}

	
}
