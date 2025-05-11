package com.fhtw.tpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.fhtw.tpserver.model")
public class TourplannerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourplannerServerApplication.class, args);
	}

}
