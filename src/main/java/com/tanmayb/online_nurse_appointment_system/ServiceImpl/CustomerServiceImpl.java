package com.tanmayb.online_nurse_appointment_system.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanmayb.online_nurse_appointment_system.Authentication.PasswordEncoder;
import com.tanmayb.online_nurse_appointment_system.DTO.CustomerDTO;
import com.tanmayb.online_nurse_appointment_system.DTO.CustomerRegistrationDTO;
import com.tanmayb.online_nurse_appointment_system.Entity.Credential;
import com.tanmayb.online_nurse_appointment_system.Entity.Customer;
import com.tanmayb.online_nurse_appointment_system.Exception.DuplicateResourceFoundException;
import com.tanmayb.online_nurse_appointment_system.Exception.ResourceNotFoundException;
import com.tanmayb.online_nurse_appointment_system.Mapper.CustomerMapper;
import com.tanmayb.online_nurse_appointment_system.Repository.AppointmentRepository;
import com.tanmayb.online_nurse_appointment_system.Repository.CredentialRepository;
import com.tanmayb.online_nurse_appointment_system.Repository.CustomerRepository;
import com.tanmayb.online_nurse_appointment_system.Service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    
    @Override
    public CustomerRegistrationDTO registerCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        
        Customer customer = CustomerMapper.mapToCustomer(customerRegistrationDTO);
        
        Credential credential = CustomerMapper.mapToCustomerCredential(customerRegistrationDTO);
        
        if(credentialRepository.existsById(credential.getEntityUsername())) throw new DuplicateResourceFoundException("customerUsername: " + credential.getEntityUsername() + " | Error: Username already present in database.");
        
        String customerPassword = credential.getEntityPassword();
        
        credential.setEntityPassword(PasswordEncoder.encodePassword(customerPassword));
        
        Customer registeredCustomer = customerRepository.save(customer);
        
        Credential registeredCredential = credentialRepository.save(credential);
        
        registeredCredential.setEntityPassword(customerPassword);
        
        return CustomerMapper.mapToCustomerRegistrationDTO(registeredCustomer, registeredCredential);
        
    }
    
    @Override
    public CustomerDTO updateCustomer(Integer customerID, CustomerDTO customerDTO) {
        
        Customer customer = customerRepository.findById(customerID).orElseThrow(
            
            () -> new ResourceNotFoundException("customerID: " + customerID + " | Error: Customer not present in database.")
            
        );
        
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());
        customer.setCustomerContact(customerDTO.getCustomerContact());
        
        Customer updatedCustomer = customerRepository.save(customer);
        
        return CustomerMapper.mapToCustomerDTO(updatedCustomer);
        
    }
    
    @Override
    public void deleteCustomer(Integer customerID) {
        
        Customer customer = customerRepository.findById(customerID).orElseThrow(
            
            () -> new ResourceNotFoundException("customerID: " + customerID + " | Error: Customer not present in database.")
            
        );
        
        credentialRepository.deleteById(customer.getCustomerUsername());
        
        appointmentRepository.deleteAllByCustomerCustomerID(customerID);
        
        customerRepository.deleteById(customerID);
        
    }
    
    private
        
        CustomerRepository customerRepository;
        CredentialRepository credentialRepository;
        AppointmentRepository appointmentRepository;
        
}