package com.example.microserviceproject1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microserviceproject1.bean.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>
{

}
