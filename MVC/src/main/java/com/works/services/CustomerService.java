package com.works.services;

import com.works.entities.Customer;
import com.works.entities.dto.CustomerLoginDto;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer login(CustomerLoginDto customerLoginDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(customerLoginDto.getEmail(), customerLoginDto.getPassword());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return customer;
        }
        return null;
    }

}
