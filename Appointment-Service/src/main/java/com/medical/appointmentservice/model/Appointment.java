package com.medical.appointmentservice.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// @Data: Génère les Getters et Setters pour tous les champs
// @NoArgsConstructor: Génère un constructeur sans arguments (requis par JPA)
// @AllArgsConstructor: Utile pour les tests ou la création d'instances simples
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Entity

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;
    private Long patientId;
    private LocalDateTime dateHeure;
    private String motif;
    private String statut = "CONFIRME";


}