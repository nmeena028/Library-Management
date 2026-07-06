package com.example.LibraryManagement.Repo;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepo extends JpaRepository<Borrow,Long> {
    boolean existsByBookAndReturnedFalse(Book book);
    boolean existsByBookAndCustomerAndReturnedFalse(Book book, Customer customer);

    List<Borrow> findByReturnedFalse();
}
