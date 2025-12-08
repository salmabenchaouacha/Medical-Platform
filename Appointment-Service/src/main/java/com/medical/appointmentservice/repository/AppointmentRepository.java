package com.medical.appointmentservice.repository;

import com.medical.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // ✅ OK : findByPatientId correspond au champ private Long patientId;
    List<Appointment> findByPatientId(Long patientId);

    // ✅ OK : findByDoctorId correspond au champ private Long doctorId;
    List<Appointment> findByDoctorId(Long doctorId);

    // ⭐ CORRECTION FINALE : findByDoctorIdAndDateHeure correspond au champ private LocalDateTime dateHeure;
    List<Appointment> findByDoctorIdAndDateHeure(Long doctorId, LocalDateTime dateHeure);
    //                                ^^^^^^^^^^ Le nom du champ en CamelCase
}