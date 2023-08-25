package com.tanmayb.online_nurse_appointment_system.Mapper;

import com.tanmayb.online_nurse_appointment_system.DTO.AppointmentDTO;
import com.tanmayb.online_nurse_appointment_system.DTO.AppointmentViewDTO;
import com.tanmayb.online_nurse_appointment_system.Entity.Appointment;
import com.tanmayb.online_nurse_appointment_system.Entity.Customer;
import com.tanmayb.online_nurse_appointment_system.Entity.HealthCare;
import com.tanmayb.online_nurse_appointment_system.Entity.Nurse;

public class AppointmentMapper {
    
    public static Appointment mapToAppointment(Integer appointmentID, Customer customer, Nurse nurse, HealthCare healthCare, Boolean paymentStatus) {
        
        return new Appointment(appointmentID, customer, nurse, healthCare, paymentStatus);
        
    }
    
    public static AppointmentDTO mapToAppointmentDTO(Integer appointmentID, Integer customerID, Integer nurseID, Integer healthCareID, Boolean paymentStatus) {
        
        return new AppointmentDTO(appointmentID, customerID, nurseID, healthCareID, paymentStatus);
        
    }
    
    public static AppointmentViewDTO mapToAppointmentViewDTO(Appointment appointment) {
        
        return new AppointmentViewDTO(appointment.getAppointmentID(), CustomerMapper.mapToCustomerDTO(appointment.getCustomer()), NurseMapper.mapToNurseDTO(appointment.getNurse()), HealthCareMapper.mapToHealthCareDTO(appointment.getHealthCare()), appointment.getPaymentStatus());
        
    }
    
}