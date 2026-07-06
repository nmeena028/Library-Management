package com.example.LibraryManagement.Repo;

import com.example.LibraryManagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);

}
