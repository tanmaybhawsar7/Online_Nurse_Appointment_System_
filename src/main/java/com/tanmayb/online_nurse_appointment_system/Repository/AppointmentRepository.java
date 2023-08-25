package com.tanmayb.online_nurse_appointment_system.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanmayb.online_nurse_appointment_system.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
    List<Appointment> findByCustomerCustomerID(Integer customerID);
    
    List<Appointment> findByNurseNurseID(Integer nurseID);
    
    List<Appointment> findByHealthCareHealthCareID(Integer healthCareID);
    
    void deleteAllByCustomerCustomerID(Integer customerID);
    
    void deleteAllByHealthCareHealthCareID(Integer healthCareID);
    
}