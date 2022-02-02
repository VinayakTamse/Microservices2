package com.example.microserviceproject1.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	
	

	

	@Id
	@Column(name="Emp_Id")
	int empId;
	
	@Column(name="Emp_Name")
	String empName;
	
	@Column(name="Salary")
	int Salary;
	
public Employee(int empId, String empName, int salary) {
		
		this.empId = empId;
		this.empName = empName;
		this.Salary = salary;
	}
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		this.Salary = salary;
	}

	public Employee()
	{
		
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", Salary=" + Salary + "]";
	}
	

	

}
