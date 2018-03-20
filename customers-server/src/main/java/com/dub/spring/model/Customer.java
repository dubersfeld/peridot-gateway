package com.dub.spring.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "city")
	private String city;
	
	public Customer() {}
	
	public Customer(long id, String firstName, String lastName, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.city = city;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return id + " " + firstName + " " + lastName + " " + city;
	}
	
	public Customer withId(long id) {
		this.id = id;
		return this;
	}
	
	public Customer withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public Customer withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public Customer withCity(String city) {
		this.city = city;
		return this;
	}
	
}
