package com.example.subscriptions_watcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service for watching the newsletter subscriptions.
 * 1. Build and install jars:
 * mvn clean install && mvn clean package
 * 2. Run the server:
 * dapr run --resources-path ./src/components/pubsub --app-id \
 * subscriptions-watcher --app-port 3000 -- java -jar \
 * target/subscriptions-watcher-0.0.1-SNAPSHOT.jar --server.port=3000
 */
@SpringBootApplication
public class SubscriptionsWatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionsWatcherApplication.class, args);
	}

}
