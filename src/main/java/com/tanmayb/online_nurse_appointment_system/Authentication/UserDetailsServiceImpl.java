package com.tanmayb.online_nurse_appointment_system.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tanmayb.online_nurse_appointment_system.Entity.Credential;
import com.tanmayb.online_nurse_appointment_system.Repository.CredentialRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String entityUsername) throws UsernameNotFoundException {
        
        Credential credential = credentialRepository.findByEntityUsername(entityUsername);
        
        if(credential == null) throw new UsernameNotFoundException("username: " + entityUsername + " | Error: Username not registered.");
        
        return new UserDetailsImpl(credential);
        
    }
    
    @Autowired
    private CredentialRepository credentialRepository;
    
}