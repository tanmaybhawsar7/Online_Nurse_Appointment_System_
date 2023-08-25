package com.tanmayb.online_nurse_appointment_system.Service;

import com.tanmayb.online_nurse_appointment_system.DTO.CustomerDTO;
import com.tanmayb.online_nurse_appointment_system.DTO.CustomerRegistrationDTO;

public interface CustomerService {
    
    CustomerRegistrationDTO registerCustomer(CustomerRegistrationDTO customerRegistrationDTO);
    
    CustomerDTO updateCustomer(Integer customerID, CustomerDTO customerDTO);
    
    void deleteCustomer(Integer customerID);
    
}