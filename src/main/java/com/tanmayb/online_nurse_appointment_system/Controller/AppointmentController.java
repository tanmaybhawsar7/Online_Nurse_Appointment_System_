package com.tanmayb.online_nurse_appointment_system.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmayb.online_nurse_appointment_system.DTO.AppointmentDTO;
import com.tanmayb.online_nurse_appointment_system.DTO.AppointmentViewDTO;
import com.tanmayb.online_nurse_appointment_system.Service.AppointmentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/appointment")
public class AppointmentController {
    
    @GetMapping("/view/customer/{customerID}")
    public ResponseEntity<List<AppointmentViewDTO>> viewAppointmentsOfCustomer(@PathVariable("customerID") Integer customerID) {
        
        List<AppointmentViewDTO> appointmentList = appointmentService.viewAppointmentsOfCustomer(customerID);
        
        return new ResponseEntity<List<AppointmentViewDTO>>(appointmentList, HttpStatus.OK);
        
    }
    
    @GetMapping("/view/nurse/{nurseID}")
    public ResponseEntity<List<AppointmentViewDTO>> viewAppointmentsOfNurse(@PathVariable("nurseID") Integer nurseID) {
        
        List<AppointmentViewDTO> appointmentList = appointmentService.viewAppointmentsOfNurse(nurseID);
        
        return new ResponseEntity<List<AppointmentViewDTO>>(appointmentList, HttpStatus.OK);
        
    }
    
    @GetMapping("/view/healthcare/{healthCareID}")
    public ResponseEntity<List<AppointmentViewDTO>> viewAppointmentsOfHealthCare(@PathVariable("healthCareID") Integer healthCareID) {
        
        List<AppointmentViewDTO> appointmentList = appointmentService.viewAppointmentsOfHealthCare(healthCareID);
        
        return new ResponseEntity<List<AppointmentViewDTO>>(appointmentList, HttpStatus.OK);
        
    }
    
    @PostMapping("/book")
    public ResponseEntity<AppointmentDTO> bookAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        
        AppointmentDTO appointment = appointmentService.bookAppointment(appointmentDTO);
        
        return new ResponseEntity<AppointmentDTO>(appointment, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/update/{appointmentID}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable("appointmentID") Integer appointmentID, @RequestBody AppointmentDTO appointmentDTO) {
        
        AppointmentDTO appointment = appointmentService.updateAppointment(appointmentID, appointmentDTO);
        
        return ResponseEntity.ok(appointment);
        
    }
    
    @DeleteMapping("/delete/{appointmentID}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("appointmentID") Integer appointmentID) {
        
        appointmentService.deleteAppointment(appointmentID);
        
        return ResponseEntity.ok("appointmentID: " + appointmentID + " | Status: Appointment successfully deleted from database.");
        
    }
    
    @PatchMapping("/pay/{appointmentID}")
    public ResponseEntity<String> payForAppointment(@PathVariable("appointmentID") Integer appointmentID) {
        
        appointmentService.payForAppointment(appointmentID);
        
        return ResponseEntity.ok("appointmentID: " + appointmentID + " | Status: Appointment charges paid successfully.");
        
    }
    
    private AppointmentService appointmentService;
    
}