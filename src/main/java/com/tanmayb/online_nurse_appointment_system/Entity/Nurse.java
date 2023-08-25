package com.tanmayb.online_nurse_appointment_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "Nurse")
public class Nurse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nurseID")
    private Integer nurseID;
    
    @Column(name = "nurseUsername", length = 20)
    private String nurseUsername;
    
    @Column(name = "nurseName", length = 50)
    private String nurseName;
    
    @Column(name = "nurseAddress", length = 90)
    private String nurseAddress;
    
    @Column(name = "nurseContact", length = 10)
    private String nurseContact;
    
}