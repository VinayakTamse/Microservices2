package com.example.microserviceproject1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.microserviceproject1.bean.Country;
import com.example.microserviceproject1.repositories.CountryRepository;
import com.example.microserviceproject1.service.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ServiceMockitoTest.class})
public class ServiceMockitoTest {
	
	@Mock
	CountryRepository countryRep;
	
	@InjectMocks
	CountryService countryService;
	
	@Test
	@Order(1)
	public void test_getAllCountries()
	{
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington DC"));
		when(countryRep.findAll()).thenReturn(myCountries);
		assertEquals(2,countryService.getAllCountries().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById()
	{
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington DC"));
		Country c = myCountries.get(0);
		when(countryRep.findById(c.getCountryId())).thenReturn(Optional.of(c));
		String countryName = countryService.getCountryById(c.getCountryId()).getCountryName();
		System.out.println(countryName);
		assertEquals(c.getCountryName(), countryName);
		
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName()
	{
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington DC"));
		String CountryName = "USA";
		when(countryRep.findAll()).thenReturn(myCountries);
		List<Country> countryLists = countryService.getCountryByName(CountryName);
		for (Country list : countryLists)
		{
			assertEquals("USA", list.getCountryName());
		}
	}
	
	@Test
	@Order(4)
	public void test_addCountry()
	{
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington DC"));
		Country germ = new Country(3, "Germany", "Berlin");
		when(countryRep.save(germ)).thenReturn(germ);
		Country newcountry = countryService.createCountry(germ);
		assertEquals(germ, newcountry);
		assertEquals(germ.getCountryName(), newcountry.getCountryName());
	}
	
	@Test
	@Order(5)
	public void test_updateCountry()
	{
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington DC"));
		Country updateCountry = new Country(2, "Germany", "Berlin");
		when(countryRep.save(updateCountry)).thenReturn(updateCountry);
		assertEquals(updateCountry.getCountryName(),countryService.updateCountry(updateCountry).getCountryName());
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry()
	{
		List<Country> myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "India", "Delhi"));
		myCountries.add(new Country(2, "USA", "Washington DC"));
		countryService.deleteCountry(2);
		verify(countryRep, times(1)).deleteById(2);
		
	}

}
