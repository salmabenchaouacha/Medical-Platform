package com.medical.doctorservice;

import com.medical.doctorservice.model.Availability;
import com.medical.doctorservice.model.Doctor;
import com.medical.doctorservice.repository.AvailabilityRepository;
import com.medical.doctorservice.repository.DoctorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient // TP3: Active l'enregistrement auprès d'Eureka
public class DoctorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorServiceApplication.class, args);
    }

    /**
     * TP2: Initialisation de la base de données (PostgreSQL) avec des données de test.
     */
    @Bean
    public CommandLineRunner initDatabase(DoctorRepository doctorRepo, AvailabilityRepository availabilityRepo) {
        return args -> {
            // 1. Création de docteurs
            Doctor d1 = doctorRepo.save(new Doctor(null, "Dr. Dupont", "Généraliste"));
            Doctor d2 = doctorRepo.save(new Doctor(null, "Dr. Lebrun", "Pédiatre"));

            // 2. Création de disponibilités (libres) pour Dr. Dupont (ID 1)
            availabilityRepo.saveAll(List.of(
                    new Availability(null, d1.getId(), LocalDateTime.now().plusDays(1).withHour(10).withMinute(0), false),
                    new Availability(null, d1.getId(), LocalDateTime.now().plusDays(1).withHour(11).withMinute(0), false)
            ));

            // 3. Création de disponibilités pour Dr. Lebrun (ID 2)
            availabilityRepo.saveAll(List.of(
                    new Availability(null, d2.getId(), LocalDateTime.now().plusDays(2).withHour(9).withMinute(0), false)
            ));

            System.out.println("Données initialisées pour Doctor-Service sur PostgreSQL.");
        };
    }
}