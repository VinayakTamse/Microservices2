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

import com.example.microserviceproject1.bean.Employee;
import com.example.microserviceproject1.service.EmployeeService;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService empServ; 
	
	@GetMapping("/all")
	public List<Employee> getAllEmployee()
	{
		return empServ.getAllEmp();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") int id)
	{
		try {
		Employee emp = empServ.getEmpById(id);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/name")
	public ResponseEntity<Employee> getEmployeeByName(@RequestParam(value="ename") String ename) {
		try {
		Employee emp = empServ.getEmpByName(ename);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp)
	{
		try {
		Employee employee = empServ.createEmp(emp);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") int id, @RequestBody Employee emp)
	{
		try {
		Employee existEmp = empServ.getEmpById(id);
		existEmp.setEmpName(emp.getEmpName());
		existEmp.setSalary(emp.getSalary());
		Employee updateEmp = empServ.updateEmp(existEmp);
		return new ResponseEntity<Employee>(updateEmp, HttpStatus.ACCEPTED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value="id") int id)
	{
		try {
			
			String data = empServ.deleteEmp(id);
			return new ResponseEntity<String>(data, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
