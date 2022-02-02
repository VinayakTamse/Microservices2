package com.example.microserviceproject1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microserviceproject1.bean.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
