package com.losstname.galaxymerchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GalaxymerchantApplication {

	static Map<String, String> tokenRomanValueMapping = new HashMap<String, String>();
	static Map<String, Float> tokenIntegerValue = new HashMap<String, Float>();
	static Map<String, String> questionAndReply = new HashMap<String, String>();
	static ArrayList<String> missingValues = new ArrayList<String>();
	static Map<String, Float> elementValueList = new HashMap<String, Float>();

	public static void main(String[] args) {
		SpringApplication.run(GalaxymerchantApplication.class, args);

		System.out.println(" ** Welcome to Galaxy Merchant Trading Guide **");
		InputRead.processInput();
		InputRead.MapTokentoIntegerValue();
		OutputProcess.processReplyForQuestion();

	}

}
