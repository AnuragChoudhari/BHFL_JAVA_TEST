package com.example.bhfl.service;

import com.example.bhfl.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate;

    @Autowired
    public WebhookService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WebhookResponse generateWebhook() {
        try {
            String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("name", "Anurag Choudhari"); 
            requestBody.put("regNo", "1262240440"); 
            requestBody.put("email", "choudharianurag30@gmail.com"); 

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            System.out.println("Sending request to: " + url);
            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(url, request, WebhookResponse.class);
            System.out.println("Response status: " + response.getStatusCode());

            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error in generateWebhook: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void submitFinalQuery(String webhookUrl, String token, String finalQuery) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token); // Adds Authorization: Bearer <token>

            Map<String, String> body = new HashMap<>();
            body.put("finalQuery", finalQuery);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            System.out.println("Submitting to webhook: " + webhookUrl);
            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);

            System.out.println("Submission Status: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error in submitFinalQuery: " + e.getMessage());
            e.printStackTrace();
        }
    }
}