package com.example.newsletter_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Service for watching the newsletter subscriptions.
 * 1. Build and install jars:
 * mvn clean install && mvn clean package
 * 2. Run the server:
 * dapr run --resources-path ./components/bindings,./components/pubsub \
 * --app-id newsletter-admin --app-port 3001 -- java -jar \
 * target/newsletter-admin-0.0.1-SNAPSHOT.jar --server.port=3001
 */
@SpringBootApplication
public class NewsletterAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsletterAdminApplication.class, args);
	}

}
