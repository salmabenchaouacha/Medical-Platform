package com.medical.doctorservice.service;


import com.medical.doctorservice.model.Availability;
import com.medical.doctorservice.model.Doctor;
import com.medical.doctorservice.repository.AvailabilityRepository;
import com.medical.doctorservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AvailabilityRepository availabilityRepository;

    // TP7: Retourne un Flux (réactif)
    public Flux<Doctor> findAllDoctors() {
        return Flux.fromIterable(doctorRepository.findAll());
    }

    // TP7: Retourne un Mono (réactif)
    public Mono<Doctor> findDoctorById(Long id) {
        return Mono.justOrEmpty(doctorRepository.findById(id));
    }

    // TP7: Retourne un Flux des créneaux libres
    public Flux<Availability> findAvailableSlots(Long doctorId) {
        return Flux.fromIterable(
                availabilityRepository.findByDoctorIdAndIsBookedFalse(doctorId)
        );
    }

    // Retourne un Mono pour vérifier un créneau spécifique
    public Mono<Availability> findAvailabilityById(Long id) {
        return Mono.justOrEmpty(availabilityRepository.findById(id));
    }
}
