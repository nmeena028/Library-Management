package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entity.Customer;
import com.example.LibraryManagement.Repo.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public void addCustomer(Customer customer) {
        if(customerRepo.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        customerRepo.save(customer);
    }


}
