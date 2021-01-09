package com.example.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateApplication.class, args);
	}

}
