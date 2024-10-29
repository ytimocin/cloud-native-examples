package com.example.newsletter_admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dapr.client.DaprClient;

@RestController
public class NewsletterAdminController {

  private static final String BINDING_NAME = "s3";
  private static final String OPERATION = "create";

  private static final String PUBSUB_NAME = "pubsub";
  private static final String TOPIC_NAME = "uploads";

  private final DaprClient daprClient;
  private final ObjectMapper objectMapper;

  public NewsletterAdminController(DaprClient daprClient, ObjectMapper objectMapper) {
    this.daprClient = daprClient;
    this.objectMapper = objectMapper;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      Map<String, String> metadata = new HashMap<>();
      metadata.put("key", file.getOriginalFilename());

      // Invoke the S3 binding to upload the file.
      this.daprClient.invokeBinding(BINDING_NAME, OPERATION, file.getBytes(), metadata).block();

      // Write to Redis.
      this.daprClient.publishEvent(PUBSUB_NAME, TOPIC_NAME, file.getOriginalFilename()).block();

      return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("Error uploading file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/copy")
  public ResponseEntity<String> copyFile(@RequestParam("source") String source,
      @RequestParam("destinationKey") String destinationKey,
      @RequestParam("bucket") String bucket) {
    try {
      Map<String, String> metadata = new HashMap<>();

      // Bucket is the destination bucket.
      metadata.put("bucket", bucket);
      metadata.put("source", source);
      metadata.put("destinationKey", destinationKey);

      // Invoke the S3 binding to copy the file.
      this.daprClient.invokeBinding(BINDING_NAME, "copy", null, metadata).block();

      return new ResponseEntity<>("File copied successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error copying file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // An endpoint to list all subscribers of the newsletter.
  // Postgres Dapr component is used to store and list the subscribers.
  @GetMapping("/subscribers")
  public ResponseEntity<List<Map<String, Object>>> getSubscriptions() {
    try {
      String query = "SELECT * FROM subscriptions";
      Map<String, String> metadata = new HashMap<>();
      metadata.put("sql", query);

      byte[] result = this.daprClient.invokeBinding("postgres", "query", null, metadata).block();

      // Convert the result to a List of Maps
      Map<String, Object> responseMap = this.objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
      });
      @SuppressWarnings("unchecked")
      List<Map<String, Object>> subscribers = (List<Map<String, Object>>) responseMap.get("data");

      return new ResponseEntity<>(subscribers, HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
