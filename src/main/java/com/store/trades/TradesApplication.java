package com.store.trades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradesApplication.class, args);
	}

}
