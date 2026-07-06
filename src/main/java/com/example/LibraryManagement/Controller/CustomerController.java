package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entity.Customer;
import com.example.LibraryManagement.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<Customer>> GetAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> AddCategory(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Successfully Added");
    }
}
