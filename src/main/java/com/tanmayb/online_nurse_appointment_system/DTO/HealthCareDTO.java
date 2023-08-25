package com.tanmayb.online_nurse_appointment_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HealthCareDTO {
    
    private
        
        Integer healthCareID;
        String healthCareDescription;
        Double healthCareCharge;
        
}