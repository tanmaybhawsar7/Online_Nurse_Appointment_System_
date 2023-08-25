package com.tanmayb.online_nurse_appointment_system.Mapper;

import com.tanmayb.online_nurse_appointment_system.DTO.HealthCareDTO;
import com.tanmayb.online_nurse_appointment_system.Entity.HealthCare;

public class HealthCareMapper {
    
    public static HealthCare mapToHealthCare(HealthCareDTO healthCareDTO) {
        
        return new HealthCare(healthCareDTO.getHealthCareID(), healthCareDTO.getHealthCareDescription(), healthCareDTO.getHealthCareCharge());
        
    }
    
    public static HealthCareDTO mapToHealthCareDTO(HealthCare healthCare) {
        
        return new HealthCareDTO(healthCare.getHealthCareID(), healthCare.getHealthCareDescription(), healthCare.getHealthCareCharge());
        
    }
    
}