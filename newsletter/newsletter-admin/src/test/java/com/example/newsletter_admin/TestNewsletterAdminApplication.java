package com.example.newsletter_admin;

import org.springframework.boot.SpringApplication;

public class TestNewsletterAdminApplication {

	public static void main(String[] args) {
		SpringApplication.from(NewsletterAdminApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
