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
@Table(name = "Customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private Integer customerID;
    
    @Column(name = "customerUsername", length = 20)
    private String customerUsername;
    
    @Column(name = "customerName", length = 50)
    private String customerName;
    
    @Column(name = "customerAddress", length = 90)
    private String customerAddress;
    
    @Column(name = "customerContact", length = 10)
    private String customerContact;
    
}