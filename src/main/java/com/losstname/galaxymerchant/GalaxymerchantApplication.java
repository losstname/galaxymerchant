package com.losstname.galaxymerchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GalaxymerchantApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxymerchantApplication.class, args);

		System.out.println(" ** Welcome to Galaxy Merchant Trading Guide **");
		InputReadAndProcess.processInput();

	}

}
