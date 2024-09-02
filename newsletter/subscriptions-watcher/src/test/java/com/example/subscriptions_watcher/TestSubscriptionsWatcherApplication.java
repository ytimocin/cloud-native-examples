package com.example.subscriptions_watcher;

import org.springframework.boot.SpringApplication;

public class TestSubscriptionsWatcherApplication {

	public static void main(String[] args) {
		SpringApplication.from(SubscriptionsWatcherApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
