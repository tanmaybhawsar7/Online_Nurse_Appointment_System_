package com.tanmayb.online_nurse_appointment_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanmayb.online_nurse_appointment_system.Entity.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {}