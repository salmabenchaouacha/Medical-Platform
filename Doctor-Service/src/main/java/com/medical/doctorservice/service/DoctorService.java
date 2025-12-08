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
    /**
     * Marque un créneau horaire spécifique comme "réservé" (booked).
     * @param id L'ID du créneau (Availability) à réserver.
     * @return Mono<Availability> Le créneau mis à jour.
     */
//    public Mono<Availability> bookAvailability(Long id) {
//        return Mono.fromCallable(() -> {
//            // 1. Chercher le créneau
//            Availability availability = availabilityRepository.findById(id)
//                    .orElseThrow(() -> new RuntimeException("Availability not found"));
//
//            // 2. Vérifier s'il est déjà réservé
//            if (availability.isBooked()) {
//                throw new RuntimeException("Slot already booked");
//            }
//
//            // 3. Mettre à jour
//            availability.setBooked(true);
//
//            // 4. Sauvegarder
//            return availabilityRepository.save(availability);
//        }).subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic());
//    }

    public Mono<Availability> toggleBooking(Long id) {
        return Mono.fromCallable(() -> {
            Availability availability = availabilityRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Availability not found"));

            // ⬅️ Toggle : inverse la valeur actuelle
            availability.setBooked(!availability.isBooked());

            return availabilityRepository.save(availability);
        }).subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic());
    }



}
