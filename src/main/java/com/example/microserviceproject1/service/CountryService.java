package com.example.microserviceproject1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.microserviceproject1.bean.Country;
import com.example.microserviceproject1.repositories.CountryRepository;

@Component
@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRepo;
	
	public List<Country> getAllCountries()
	{
		List<Country> countryLists = countryRepo.findAll();
		return countryLists;
	}
	
	public Country getCountryById(int id)
	{
		return countryRepo.findById(id).get();
	}
	
	public List<Country> getCountryByName(String name)
	{
		List<Country> countryLists = countryRepo.findAll();
		List<Country> newCountry = new ArrayList<Country>();
		for (Country c : countryLists)
		{
		 if (c.getCountryName().equalsIgnoreCase(name))
		 {
			newCountry.add(c);
		 }
		}
		
		return newCountry;
	}
	
	public Country createCountry(Country country)
	{
		countryRepo.save(country);
		List<Country> countryLists = countryRepo.findAll();
		for (Country c : countryLists)
		{
			if (c.getCountryName().equals(country.getCountryName()))
			{
				country = c;
			}
		}
		return country;
	}
	
	public Country updateCountry(Country country)
	{
		countryRepo.save(country);
		return country;
	}
	
	public String deleteCountry(int id)
	{
		countryRepo.deleteById(id);
		return "Deleted country with id "+id;
	}

}
