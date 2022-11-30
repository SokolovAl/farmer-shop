package ru.aston.farmershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class FarmerShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmerShopApplication.class, args);
	}

}
