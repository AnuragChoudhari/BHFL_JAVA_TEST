package com.example.bhfl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.bhfl")
public class BHFLApplication {

    public static void main(String[] args) {
        SpringApplication.run(BHFLApplication.class, args);
    }
}