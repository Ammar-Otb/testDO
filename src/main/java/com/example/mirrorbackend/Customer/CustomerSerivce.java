package com.example.mirrorbackend.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CustomerSerivce {
    private final CustomerRepository customerRepository;
    public List<Customer> getAllUsers(){
        return customerRepository.findAll();
    }
    public void registerCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public Customer getCustomer(String customerId){
        return customerRepository.findById(customerId).get();
    }
}
