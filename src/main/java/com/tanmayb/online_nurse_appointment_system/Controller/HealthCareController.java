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

import com.tanmayb.online_nurse_appointment_system.DTO.HealthCareDTO;
import com.tanmayb.online_nurse_appointment_system.Service.HealthCareService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/healthcare")
public class HealthCareController {
    
    @PostMapping("/register")
    public ResponseEntity<HealthCareDTO> registerHealthCare(@RequestBody HealthCareDTO healthCareDTO) {
        
        HealthCareDTO healthCare = healthCareService.registerHealthCare(healthCareDTO);
        
        return new ResponseEntity<HealthCareDTO>(healthCare, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/update/{healthCareID}")
    public ResponseEntity<HealthCareDTO> updateHealthCare(@PathVariable("healthCareID") Integer healthCareID, @RequestBody HealthCareDTO healthCareDTO) {
        
        HealthCareDTO healthCare = healthCareService.updateHealthCare(healthCareID, healthCareDTO);
        
        return ResponseEntity.ok(healthCare);
        
    }
    
    @DeleteMapping("/delete/{healthCareID}")
    public ResponseEntity<String> deleteHealthCare(@PathVariable("healthCareID") Integer healthCareID) {
        
        healthCareService.deleteHealthCare(healthCareID);
        
        return ResponseEntity.ok("healthCareID: " + healthCareID + " | Status: HealthCare successfully deleted from database.");
        
    }
    
    private HealthCareService healthCareService;
    
}