package com.azmeer.passwordmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // ← MAGIC: Starts your entire app
public class SecurityModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityModuleApplication.class, args);
    }
}
