package com.example.microserviceproject1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microserviceproject1.bean.Country;
import com.example.microserviceproject1.service.CountryService;

@RequestMapping("/countries")
@RestController
public class CountryController {

	@Autowired
	CountryService countrySev;

	@GetMapping("/all")
	public ResponseEntity<List<Country>> getAllCountries() {
		try {
		List<Country> countryLists = countrySev.getAllCountries();
		return new ResponseEntity<List<Country>>(countryLists, HttpStatus.FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {
		try {
			Country country = countrySev.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/countryname")
	public ResponseEntity<List<Country>> getCountryName(@RequestParam(value = "name") String name) {
		try {
			List<Country> country = countrySev.getCountryByName(name);
			return new ResponseEntity<List<Country>>(country, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {
		try {
		Country newCountry =  countrySev.createCountry(country);
		return new ResponseEntity<Country>(newCountry, HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country) {
		try {
			Country existCountry = countrySev.getCountryById(id);
			existCountry.setCountryName(country.getCountryName());
			existCountry.setCountryCapital(country.getCountryCapital());
			Country updatedCountry = countrySev.updateCountry(existCountry);
			return new ResponseEntity<Country>(updatedCountry, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCountry(@PathVariable(value="id") int id)
	{
		return countrySev.deleteCountry(id);
	}

}
