package com.tanmayb.online_nurse_appointment_system.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tanmayb.online_nurse_appointment_system.DTO.AppointmentDTO;
import com.tanmayb.online_nurse_appointment_system.DTO.AppointmentViewDTO;
import com.tanmayb.online_nurse_appointment_system.Entity.Appointment;
import com.tanmayb.online_nurse_appointment_system.Entity.Customer;
import com.tanmayb.online_nurse_appointment_system.Entity.HealthCare;
import com.tanmayb.online_nurse_appointment_system.Entity.Nurse;
import com.tanmayb.online_nurse_appointment_system.Exception.ResourceNotFoundException;
import com.tanmayb.online_nurse_appointment_system.Mapper.AppointmentMapper;
import com.tanmayb.online_nurse_appointment_system.Repository.AppointmentRepository;
import com.tanmayb.online_nurse_appointment_system.Repository.CustomerRepository;
import com.tanmayb.online_nurse_appointment_system.Repository.HealthCareRepository;
import com.tanmayb.online_nurse_appointment_system.Repository.NurseRepository;
import com.tanmayb.online_nurse_appointment_system.Service.AppointmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    
    @Override
    public List<AppointmentViewDTO> viewAppointmentsOfCustomer(Integer customerID) {
        
        customerRepository.findById(customerID).orElseThrow(
            
            () -> new ResourceNotFoundException("customerID: " + customerID + " | Error: Customer not present in database.")
            
        );
        
        List<Appointment> appointmentList = appointmentRepository.findByCustomerCustomerID(customerID);
        
        if(appointmentList.isEmpty()) throw new ResourceNotFoundException("customerID: " + customerID + " | Status: Zero appointments present in database.");
        
        return appointmentList.stream().map((appointment) -> AppointmentMapper.mapToAppointmentViewDTO(appointment)).collect(Collectors.toList());
        
    }
    
    @Override
    public List<AppointmentViewDTO> viewAppointmentsOfNurse(Integer nurseID) {
        
        nurseRepository.findById(nurseID).orElseThrow(
            
            () -> new ResourceNotFoundException("nurseID: " + nurseID + " | Error: Nurse not present in database.")
            
        );
        
        List<Appointment> appointmentList = appointmentRepository.findByNurseNurseID(nurseID);
        
        if(appointmentList.isEmpty()) throw new ResourceNotFoundException("nurseID: " + nurseID + " | Status: Zero appointments present in database.");
        
        return appointmentList.stream().map((appointment) -> AppointmentMapper.mapToAppointmentViewDTO(appointment)).collect(Collectors.toList());
        
    }
    
    @Override
    public List<AppointmentViewDTO> viewAppointmentsOfHealthCare(Integer healthCareID) {
        
        healthCareRepository.findById(healthCareID).orElseThrow(
            
            () -> new ResourceNotFoundException("healthCareID: " + healthCareID + " | Error: HealthCare not present in database.")
            
        );
        
        List<Appointment> appointmentList = appointmentRepository.findByHealthCareHealthCareID(healthCareID);
        
        if(appointmentList.isEmpty()) throw new ResourceNotFoundException("healthCareID: " + healthCareID + " | Status: Zero appointments present in database.");
        
        return appointmentList.stream().map((appointment) -> AppointmentMapper.mapToAppointmentViewDTO(appointment)).collect(Collectors.toList());
        
    }
    
    @Override
    public AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO) {
        
        customerRepository.findById(appointmentDTO.getCustomerID()).orElseThrow(
            
            () -> new ResourceNotFoundException("customerID: " + appointmentDTO.getCustomerID() + " | Error: Customer not present in database.")
            
        );
        
        nurseRepository.findById(appointmentDTO.getNurseID()).orElseThrow(
            
            () -> new ResourceNotFoundException("nurseID: " + appointmentDTO.getNurseID() + " | Error: Nurse not present in database.")
            
        );
        
        healthCareRepository.findById(appointmentDTO.getHealthCareID()).orElseThrow(
            
            () -> new ResourceNotFoundException("healthCareID: " + appointmentDTO.getHealthCareID() + " | Error: HealthCare not present in database.")
            
        );
        
        Appointment appointment = AppointmentMapper.mapToAppointment(appointmentDTO.getAppointmentID(), customerRepository.findById(appointmentDTO.getCustomerID()).get(), nurseRepository.findById(appointmentDTO.getNurseID()).get(), healthCareRepository.findById(appointmentDTO.getHealthCareID()).get(), false);
        
        Appointment registeredAppointment = appointmentRepository.save(appointment);
        
        return AppointmentMapper.mapToAppointmentDTO(registeredAppointment.getAppointmentID(), registeredAppointment.getCustomer().getCustomerID(), registeredAppointment.getNurse().getNurseID(), registeredAppointment.getHealthCare().getHealthCareID(), registeredAppointment.getPaymentStatus());
        
    }
    
    @Override
    public AppointmentDTO updateAppointment(Integer appointmentID, AppointmentDTO appointmentDTO) {
        
        Appointment appointment = appointmentRepository.findById(appointmentID).orElseThrow(
            
            () -> new ResourceNotFoundException("appointmentID: " + appointmentID + " | Error: Appointment not present in database.")
            
        );
        
        Customer customer = customerRepository.findById(appointmentDTO.getCustomerID()).orElseThrow(
            
            () -> new ResourceNotFoundException("customerID: " + appointmentDTO.getCustomerID() + " | Error: Customer not present in database.")
            
        );
        
        Nurse nurse = nurseRepository.findById(appointmentDTO.getNurseID()).orElseThrow(
            
            () -> new ResourceNotFoundException("nurseID: " + appointmentDTO.getNurseID() + " | Error: Nurse not present in database.")
            
        );
        
        HealthCare healthCare = healthCareRepository.findById(appointmentDTO.getHealthCareID()).orElseThrow(
            
            () -> new ResourceNotFoundException("healthCareID: " + appointmentDTO.getHealthCareID() + " | Error: HealthCare not present in database.")
            
        );
        
        appointment.setCustomer(customer);
        appointment.setNurse(nurse);
        appointment.setHealthCare(healthCare);
        
        appointmentRepository.save(appointment);
        
        return AppointmentMapper.mapToAppointmentDTO(appointmentID, customer.getCustomerID(), nurse.getNurseID(), healthCare.getHealthCareID(), appointment.getPaymentStatus());
        
    }
    
    @Override
    public void deleteAppointment(Integer appointmentID) {
        
        appointmentRepository.findById(appointmentID).orElseThrow(
            
            () -> new ResourceNotFoundException("appointmentID: " + appointmentID + " | Error: Appointment not present in database.")
            
        );
        
        appointmentRepository.deleteById(appointmentID);
        
    }
    
    @Override
    public void payForAppointment(Integer appointmentID) {
        
        Appointment appointment = appointmentRepository.findById(appointmentID).orElseThrow(
            
            () -> new ResourceNotFoundException("appointmentID: " + appointmentID + " | Error: Appointment not present in database.")
            
        );
        
        appointment.setPaymentStatus(true);
        
        appointmentRepository.save(appointment);
        
    }
    
    private
        
        AppointmentRepository appointmentRepository;
        CustomerRepository customerRepository;
        NurseRepository nurseRepository;
        HealthCareRepository healthCareRepository;
        
}