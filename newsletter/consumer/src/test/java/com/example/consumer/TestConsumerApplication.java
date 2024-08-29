package com.example.consumer;

import org.springframework.boot.SpringApplication;

public class TestConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ConsumerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
