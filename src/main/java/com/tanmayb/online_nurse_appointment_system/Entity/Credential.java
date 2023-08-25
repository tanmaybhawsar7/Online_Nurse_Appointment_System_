package com.tanmayb.online_nurse_appointment_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Credential")
public class Credential {
    
    @Id
    @Column(name = "entityUsername", length = 20)
    private String entityUsername;
    
    @Column(name = "entityPassword", length = 90)
    private String entityPassword;
    
    @Column(name = "entityRole", length = 10)
    private String entityRole;
    
}