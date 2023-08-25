package com.tanmayb.online_nurse_appointment_system.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmayb.online_nurse_appointment_system.DTO.NurseDTO;
import com.tanmayb.online_nurse_appointment_system.DTO.NurseRegistrationDTO;
import com.tanmayb.online_nurse_appointment_system.Service.NurseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/nurse")
public class NurseController {
    
    @PostMapping("/register")
    public ResponseEntity<NurseRegistrationDTO> registerNurse(@RequestBody NurseRegistrationDTO nurseRegistrationDTO) {
        
        NurseRegistrationDTO nurse = nurseService.registerNurse(nurseRegistrationDTO);
        
        return new ResponseEntity<NurseRegistrationDTO>(nurse, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/update/{nurseID}")
    public ResponseEntity<NurseDTO> updateNurse(@PathVariable("nurseID") Integer nurseID, @RequestBody NurseDTO nurseDTO) {
        
        NurseDTO nurse = nurseService.updateNurse(nurseID, nurseDTO);
        
        return ResponseEntity.ok(nurse);
        
    }
    
    @DeleteMapping("/delete/{nurseID}")
    public ResponseEntity<String> deleteNurse(@PathVariable("nurseID") Integer nurseID) {
        
        nurseService.deleteNurse(nurseID);
        
        return ResponseEntity.ok("nurseID: " + nurseID + " | Status: Nurse successfully deleted from database.");
        
    }
    
    private NurseService nurseService;
    
}