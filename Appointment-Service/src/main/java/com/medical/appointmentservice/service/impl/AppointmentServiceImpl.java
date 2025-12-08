package com.medical.appointmentservice.service.impl;

import com.medical.appointmentservice.model.Appointment;
import com.medical.appointmentservice.repository.AppointmentRepository;
import com.medical.appointmentservice.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // --- 1. Créer un rendez-vous ---
    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // --- 2. Modifier un rendez-vous ---
    @Override
    public Appointment updateAppointment(Long appointmentId, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Rendez-vous non trouvé avec ID: " + appointmentId));

        // Mise à jour des champs
        existingAppointment.setDoctorId(appointment.getDoctorId());
        existingAppointment.setPatientId(appointment.getPatientId());
        existingAppointment.setDateHeure(appointment.getDateHeure());
        existingAppointment.setMotif(appointment.getMotif());
        existingAppointment.setStatut(appointment.getStatut());

        return appointmentRepository.save(existingAppointment);
    }

    // --- 3. Annuler un rendez-vous ---
    @Override
    public void cancelAppointment(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new IllegalArgumentException("Rendez-vous non trouvé avec ID: " + appointmentId);
        }
        appointmentRepository.deleteById(appointmentId);
    }

    // --- 4. Lister les rendez-vous par patient ---
    @Override
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId); // méthode à créer dans le Repository
    }

    // --- 5. Lister les rendez-vous par médecin ---
    @Override
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId); // méthode à créer dans le Repository
    }
}
