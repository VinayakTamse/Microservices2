package com.example.microserviceproject1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.microserviceproject1.bean.Country;
import com.example.microserviceproject1.controller.CountryController;
import com.example.microserviceproject1.service.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ControllerMockitoTest.class})
public class ControllerMockitoTest {
	
	@Mock
	CountryService countrySev;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> countries;
	Country country;
	
	@Test
	@Order(1)
	public void test_get_allCountriesList()
	{
		countries = new ArrayList<Country>();
		countries.add(new Country(1,"India", "Delhi"));
		countries.add(new Country(2,"USA", "Washington DC"));
		when(countrySev.getAllCountries()).thenReturn(countries);
		ResponseEntity<List<Country>> response = countryController.getAllCountries();
		assertEquals(HttpStatus.FOUND,response.getStatusCode());
		assertEquals(countries.size(),response.getBody().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById()
	{
		country = new Country(1, "India", "Delhi");
		int countryId = 1;
		when(countrySev.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryById(countryId);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(countryId,res.getBody().getCountryId());
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName()
	{
		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country(1,"USA", "Washington DC"));
		String CountryName = "USA";
		when(countrySev.getCountryByName(CountryName)).thenReturn(countries);
		ResponseEntity<List<Country>> res = countryController.getCountryName(CountryName);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		List<Country> clists = res.getBody();
		for (Country clist : clists)
		{
			assertEquals(CountryName, clist.getCountryName());
		}
	}
	
	@Test
	@Order(4)
	public void test_addCountry()
	{
		country = new Country(3, "Germany", "Berlin");
		when(countrySev.createCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countryController.createCountry(country);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(country,res.getBody());
		
	}
	
	@Test
	@Order(5)
	public void test_updateCountry()
	{
		country = new Country(1, "USA", "Washington DC");
		int countryId = 1;
		when(countrySev.getCountryById(countryId)).thenReturn(country);
		when(countrySev.updateCountry(country)).thenReturn(country);
		ResponseEntity<Country> res =countryController.updateCountry(countryId, country);
		assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
		assertEquals(country, res.getBody());
		assertEquals(countryId, res.getBody().getCountryId());
		assertEquals("USA", res.getBody().getCountryName());
		assertEquals(country.getCountryCapital(), res.getBody().getCountryCapital());
	}
	
	@Test
	@Order(6)
	public void test_deletecountry()
	{
		country = new Country(3, "Germany", "Berlin");
		int countryId = 3;
		countryController.deleteCountry(countryId);
		verify(countrySev, times(1)).deleteCountry(countryId);
		
	}

}
