package com.tanmayb.online_nurse_appointment_system.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmayb.online_nurse_appointment_system.DTO.AdminDTO;
import com.tanmayb.online_nurse_appointment_system.Service.AdminService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {
    
    @PostMapping("/register")
    public ResponseEntity<AdminDTO> registerAdmin(@RequestBody AdminDTO adminDTO) {
        
        AdminDTO admin = adminService.registerAdmin(adminDTO);
        
        return new ResponseEntity<AdminDTO>(admin, HttpStatus.CREATED);
        
    }
    
    @DeleteMapping("/delete/{adminUsername}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("adminUsername") String adminUsername) {
        
        adminService.deleteAdmin(adminUsername);
        
        return ResponseEntity.ok("adminUsername: " + adminUsername + " | Status: Admin successfully deleted from database.");
        
    }
    
    private AdminService adminService;
    
}