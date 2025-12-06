package com.medical.doctorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medical.doctorservice.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}