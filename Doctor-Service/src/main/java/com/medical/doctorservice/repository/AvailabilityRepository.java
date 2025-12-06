package com.medical.doctorservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.medical.doctorservice.model.Availability;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDoctorIdAndIsBookedFalse(Long doctorId);
}