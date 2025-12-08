package com.medical.appointmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppointmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentServiceApplication.class, args);

        // Ajout d'un log simple pour le lien cliquable
        System.out.println("\n=======================================================");
        System.out.println("Swagger UI: http://localhost:8089/swagger-ui.html");
        System.out.println("=========================================================\n");

    }

}
