package com.medical.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openSwagger() {
        String swaggerUrl = "http://localhost:8081/swagger-ui/index.html";
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(swaggerUrl));
            } catch (IOException | URISyntaxException e) {
                System.err.println("Impossible d'ouvrir Swagger automatiquement: " + e.getMessage());
            }
        } else {
            System.out.println("Ouvre Swagger manuellement: " + swaggerUrl);
        }
    }
}
