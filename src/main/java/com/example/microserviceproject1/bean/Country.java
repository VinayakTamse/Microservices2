package com.example.microserviceproject1.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Country")
public class Country {
	
	@Id
	@Column(name="Country_Id")
	int countryId;
	
	@Column(name="Country_Name")
	String countryName;
	
	@Column(name="Country_Capital")
	String countryCapital;
	
	public Country()
	{
		
	}
	
	public Country(int countryId, String countryName, String countryCapital)
	{
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
	}
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCapital() {
		return countryCapital;
	}

	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}

	
	
	
	

}
