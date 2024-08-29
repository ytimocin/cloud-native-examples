package com.example.newsletter;

import org.springframework.boot.SpringApplication;

public class TestNewsletterApplication {

	public static void main(String[] args) {
		SpringApplication.from(NewsletterApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
