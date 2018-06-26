package com.rserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rserve.connection.StartRserve;

@SpringBootApplication
public class RserveRestApplication {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello THERE");
		StartRserve.init();
		SpringApplication.run(RserveRestApplication.class, args);
	}
}
