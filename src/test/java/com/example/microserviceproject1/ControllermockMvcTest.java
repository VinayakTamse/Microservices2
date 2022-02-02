package com.example.microserviceproject1;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.microserviceproject1.bean.Country;
import com.example.microserviceproject1.controller.CountryController;
import com.example.microserviceproject1.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.example.microserviceproject1")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllermockMvcTest.class})
public class ControllermockMvcTest {
	
	@Autowired
	MockMvc mockmvc;
	
	@Mock
	CountryService countrySev;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> countries;
	Country country;
	
	@BeforeEach
	public void setUp()
	{
		mockmvc = MockMvcBuilders.standaloneSetup(countryController).build();
	}
	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception
	{
		countries = new ArrayList<Country>();
		countries.add(new Country(1, "India", "Delhi"));
		countries.add(new Country(2, "USA", "Washington DC"));
		when(countrySev.getAllCountries()).thenReturn(countries);
		this.mockmvc.perform(get("/countries/all"))
		.andExpect(status().isFound())
			.andDo(print());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() throws Exception
	{
		country = new Country(1, "India", "Delhi");
		int countryId = 1;
		when(countrySev.getCountryById(countryId)).thenReturn(country);
		this.mockmvc.perform(get("/countries/{id}", countryId))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath(".countryId").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Delhi"))
			.andDo(print());   
			
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception
	{
		countries = new ArrayList<Country>();
		countries.add(new Country(1, "India", "Delhi"));
		String countryname = "India";
		when(countrySev.getCountryByName(countryname)).thenReturn(countries);
		this.mockmvc.perform(get("/countries/countryname").param("name", countryname))
		.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() throws Exception
	{
		country = new Country(3, "Germany", "Berlin");
		when(countrySev.createCountry(country)).thenReturn(country);
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(country);
		this.mockmvc.perform(post("/countries/create").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andDo(print());
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() throws Exception 
	{
		country = new Country(3, "Japan", "Tokyo");
		int countryId = 3;
		when(countrySev.getCountryById(countryId)).thenReturn(country);
		when(countrySev.updateCountry(country)).thenReturn(country);
		ObjectMapper mapper = new ObjectMapper();
		String JsonBody = mapper.writeValueAsString(country);
		this.mockmvc.perform(put("/countries/update/{id}", countryId).content(JsonBody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isAccepted())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Japan"))
		.andDo(print());
		
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() throws Exception
	{
		country = new Country(3, "Japan", "Tokyo");
		int countryId = 3;
		when(countrySev.deleteCountry(countryId)).thenReturn("deleted country with "+countryId);
		this.mockmvc.perform(delete("/countries/delete/{id}", countryId))
		.andExpect(status().isOk());
	}
}
