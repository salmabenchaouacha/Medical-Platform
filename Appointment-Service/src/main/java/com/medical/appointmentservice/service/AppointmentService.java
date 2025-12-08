package com.medical.appointmentservice.service;

import com.medical.appointmentservice.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    // Créer un rendez-vous sans DTO
    Appointment createAppointment(Appointment appointment);

    // Modifier un rendez-vous
    Appointment updateAppointment(Long appointmentId, Appointment appointment);

    // Annuler un rendez-vous
    void cancelAppointment(Long appointmentId);

    // Lister les rendez-vous par patient
    List<Appointment> getAppointmentsByPatientId(Long patientId);

    // Lister les rendez-vous par médecin
    List<Appointment> getAppointmentsByDoctorId(Long doctorId);
}
