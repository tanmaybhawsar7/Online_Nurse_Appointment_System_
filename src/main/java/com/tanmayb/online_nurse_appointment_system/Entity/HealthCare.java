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
@Table(name = "HealthCare")
public class HealthCare {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "healthCareID")
    private Integer healthCareID;
    
    @Column(name = "healthCareDescription", length = 90)
    private String healthCareDescription;
    
    @Column(name = "healthCareCharge")
    private Double healthCareCharge;
    
}