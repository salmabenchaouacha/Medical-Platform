package com.medical.doctorservice.controller;



import com.medical.doctorservice.model.Availability;
import com.medical.doctorservice.model.Doctor;
import com.medical.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // TP7: Retourne un Flux (API Réactive)
    @GetMapping
    public Flux<Doctor> getAllDoctors() {
        return doctorService.findAllDoctors();
    }

    // TP7: Retourne un Mono (API Réactive)
    @GetMapping("/{id}")
    public Mono<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.findDoctorById(id);
    }

    // TP7: Retourne un flux des disponibilités
    @GetMapping("/{id}/availabilities")
    public Flux<Availability> getDoctorAvailabilities(@PathVariable Long id) {
        return doctorService.findAvailableSlots(id);
    }

    // API spécifique pour la vérification de créneau (utilisée par Appointment-Service)
    @GetMapping("/availabilities/{id}")
    public Mono<Availability> getAvailabilityById(@PathVariable Long id) {
        return doctorService.findAvailabilityById(id);
    }
}