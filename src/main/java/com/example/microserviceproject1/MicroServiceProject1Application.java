package com.example.microserviceproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroServiceProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceProject1Application.class, args);
		for (int i=1; i<=5; i++)
		{
			System.out.println(i+" Starting......");
		}
		
		System.out.println("==============================Started==============================");
		
	}

}
