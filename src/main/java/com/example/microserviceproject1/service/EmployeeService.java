package com.example.microserviceproject1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.microserviceproject1.bean.Employee;
import com.example.microserviceproject1.repositories.EmployeeRepository;

@Service
@Component
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	public List<Employee> getAllEmp()
	{
		return empRepo.findAll();
	}
	
	public Employee getEmpById(int id)
	{
		return empRepo.findById(id).get();
	}
	
	public Employee getEmpByName(String name)
	{
		Employee nEmp = null;
		List<Employee> emplists = empRepo.findAll();
		for (Employee emp : emplists)
		{
			if (emp.getEmpName().equalsIgnoreCase(name))
			{
				nEmp = emp;
			}
		}
		return nEmp;
	}
	
	public Employee createEmp(Employee emp)
	{
		Employee employee = empRepo.save(emp);
		return employee;
	}
	
	public Employee updateEmp(Employee emp)
	{
		return empRepo.save(emp);
	}
	
	public String deleteEmp(int id)
	{
		empRepo.deleteById(id);
		return "Deleted Employee with id "+id;
	}

}
