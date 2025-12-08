package com.medical.appointmentservice.controller;

import com.medical.appointmentservice.model.Appointment;
import com.medical.appointmentservice.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment saved = appointmentService.createAppointment(appointment); // au lieu de save
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody Appointment updatedAppointment) {

        try {
            Appointment updated = appointmentService.updateAppointment(appointmentId, updatedAppointment); // au lieu de update
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId) {

        try {
            appointmentService.cancelAppointment(appointmentId); // au lieu de cancel
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> listAppointments(
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) Long doctorId) {

        if (patientId != null) {
            return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId)); // au lieu de getByPatientId
        }

        if (doctorId != null) {
            return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId)); // au lieu de getByDoctorId
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // Ajoutez cette méthode de test
    @GetMapping("/hello")
    public String sayHelloTest() {
        // Confirme la réussite du routage
        return "SUCCESS: Bonjour de l'Équipe 'Prise de Rendez-vous' (Appointment-Service).";
    }
}
