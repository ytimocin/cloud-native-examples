package com.example.newsletter_admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@Configuration
public class DaprConfig {

  private static final Logger logger = LoggerFactory.getLogger(DaprConfig.class);
  private static final DaprClientBuilder BUILDER = new DaprClientBuilder();

  @Bean
  public DaprClient buildDaprClient() {
    logger.info("Building DaprClient for the newsletter-admin service...");
    return BUILDER.build();
  }
}
