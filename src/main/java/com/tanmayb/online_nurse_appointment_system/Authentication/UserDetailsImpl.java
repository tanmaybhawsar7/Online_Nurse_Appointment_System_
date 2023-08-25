package com.tanmayb.online_nurse_appointment_system.Authentication;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tanmayb.online_nurse_appointment_system.Entity.Credential;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDetailsImpl implements UserDetails {
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return Collections.singleton(new SimpleGrantedAuthority(credential.getEntityRole()));
        
    }
    
    @Override
    public String getPassword() {
        
        return credential.getEntityPassword();
        
    }
    
    @Override
    public String getUsername() {
        
        return credential.getEntityUsername();
        
    }
    
    @Override
    public boolean isAccountNonExpired() {
        
        return true;
        
    }
    
    @Override
    public boolean isAccountNonLocked() {
        
        return true;
        
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
        
    }
    
    @Override
    public boolean isEnabled() {
        
        return true;
        
    }
    
    private Credential credential;
    
}