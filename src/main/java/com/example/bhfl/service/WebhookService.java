package com.example.bhfl.service;

import com.example.bhfl.model.WebhookResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service	
public class WebhookService {

	private final RestTemplate restTemplate = new RestTemplate();

    public WebhookResponse generateWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Anurag Choudhari"); 
        requestBody.put("regNo", "1262240440"); 
        requestBody.put("email", "choudharianurag30@gmail.com"); 

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(url, request, WebhookResponse.class);

        return response.getBody();
    }

    public void submitFinalQuery(String webhookUrl, String token, String finalQuery) {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token); // Adds Authorization: Bearer <token>

        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", finalQuery);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("Submission Status: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody());
    }
	
}
