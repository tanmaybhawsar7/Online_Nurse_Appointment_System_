package com.tanmayb.online_nurse_appointment_system.ServiceImpl;

import org.springframework.stereotype.Service;

import com.tanmayb.online_nurse_appointment_system.Authentication.PasswordEncoder;
import com.tanmayb.online_nurse_appointment_system.DTO.NurseDTO;
import com.tanmayb.online_nurse_appointment_system.DTO.NurseRegistrationDTO;
import com.tanmayb.online_nurse_appointment_system.Entity.Credential;
import com.tanmayb.online_nurse_appointment_system.Entity.Nurse;
import com.tanmayb.online_nurse_appointment_system.Exception.DuplicateResourceFoundException;
import com.tanmayb.online_nurse_appointment_system.Exception.ResourceNotFoundException;
import com.tanmayb.online_nurse_appointment_system.Mapper.NurseMapper;
import com.tanmayb.online_nurse_appointment_system.Repository.CredentialRepository;
import com.tanmayb.online_nurse_appointment_system.Repository.NurseRepository;
import com.tanmayb.online_nurse_appointment_system.Service.NurseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NurseServiceImpl implements NurseService {
    
    @Override
    public NurseRegistrationDTO registerNurse(NurseRegistrationDTO nurseRegistrationDTO) {
        
        Nurse nurse = NurseMapper.mapToNurse(nurseRegistrationDTO);
        
        Credential credential = NurseMapper.mapToNurseCredential(nurseRegistrationDTO);
        
        if(credentialRepository.existsById(credential.getEntityUsername())) throw new DuplicateResourceFoundException("nurseUsername: " + credential.getEntityUsername() + " | Error: Username already present in database.");
        
        String nursePassword = credential.getEntityPassword();
        
        credential.setEntityPassword(PasswordEncoder.encodePassword(nursePassword));
        
        Nurse registeredNurse = nurseRepository.save(nurse);
        
        Credential registeredCredential = credentialRepository.save(credential);
        
        registeredCredential.setEntityPassword(nursePassword);
        
        return NurseMapper.mapToNurseRegistrationDTO(registeredNurse, registeredCredential);
        
    }
    
    @Override
    public NurseDTO updateNurse(Integer nurseID, NurseDTO nurseDTO) {
        
        Nurse nurse = nurseRepository.findById(nurseID).orElseThrow(
            
            () -> new ResourceNotFoundException("nurseID: " + nurseID + " | Error: Nurse not present in database.")
            
        );
        
        nurse.setNurseName(nurseDTO.getNurseName());
        nurse.setNurseAddress(nurseDTO.getNurseAddress());
        nurse.setNurseContact(nurseDTO.getNurseContact());
        
        Nurse updatedNurse = nurseRepository.save(nurse);
        
        return NurseMapper.mapToNurseDTO(updatedNurse);
        
    }
    
    @Override
    public void deleteNurse(Integer nurseID) {
        
        Nurse nurse = nurseRepository.findById(nurseID).orElseThrow(
            
            () -> new ResourceNotFoundException("nurseID: " + nurseID + " | Error: Nurse not present in database.")
            
        );
        
        credentialRepository.deleteById(nurse.getNurseUsername());
        
        nurseRepository.deleteById(nurseID);
        
    }
    
    private
        
        NurseRepository nurseRepository;
        CredentialRepository credentialRepository;
        
}