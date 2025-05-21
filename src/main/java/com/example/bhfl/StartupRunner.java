package com.example.bhfl;

import com.example.bhfl.model.WebhookResponse;
import com.example.bhfl.service.WebhookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {  // Added 'implements CommandLineRunner'
    
    private final WebhookService webhookService;

    public StartupRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override  // Added @Override annotation
    public void run(String... args) {
        System.out.println("Application started - generating webhook...");
        
        // Generate webhook and token
        WebhookResponse response = webhookService.generateWebhook();
        
        System.out.println("Webhook URL: " + response.getWebhook());
        System.out.println("Access Token: " + response.getAccessToken());

        String finalSqlQuery = "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, " +
                "d.DEPARTMENT_NAME, COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
                "FROM EMPLOYEE e1 " +
                "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
                "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB " +
                "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME " +
                "ORDER BY e1.EMP_ID DESC;";

        System.out.println("Submitting SQL solution...");
        
        // Submit final query using token
        webhookService.submitFinalQuery(response.getWebhook(), response.getAccessToken(), finalSqlQuery);
    }
}