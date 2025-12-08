package com.medical.doctorservice.controller;

import com.medical.doctorservice.model.Availability;
import com.medical.doctorservice.model.Doctor;
import com.medical.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // TP7: Retourne un Flux (API Réactive)
    // URL : GET /doctors
    @GetMapping
    public Flux<Doctor> getAllDoctors() {
        return doctorService.findAllDoctors();
    }

    // TP7: Retourne un Mono (API Réactive)
    // URL : GET /doctors/{id}
    @GetMapping("/{id}")
    public Mono<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.findDoctorById(id);
    }

    // TP7: Retourne un flux des disponibilités d'un médecin spécifique
    // URL : GET /doctors/{id}/availabilities
    @GetMapping("/{id}/availabilities")
    public Flux<Availability> getDoctorAvailabilities(@PathVariable Long id) {
        return doctorService.findAvailableSlots(id);
    }

    // API spécifique pour la vérification de créneau (utilisée par Appointment-Service)
    // URL : GET /doctors/availabilities/{id}
    @GetMapping("/availabilities/{id}")
    public Mono<Availability> getAvailabilityById(@PathVariable Long id) {
        return doctorService.findAvailabilityById(id);
    }

    // TP8: NOUVEL ENDPOINT - API pour la réservation de créneau (modifie l'état de la ressource)
    // URL : PUT /doctors/availabilities/{id}/book
    @PutMapping("/availabilities/{id}/toggle")
    public Mono<Availability> toggleSlot(@PathVariable Long id) {
        return doctorService.toggleBooking(id);
    }
    @GetMapping("/hello")
    public String sayHello() {
        return "Bonjour du Service Docteurs.";
    }
}