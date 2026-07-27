package com.example.LibraryManagement.Repo;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepo extends JpaRepository<Borrow,Long> {
    boolean existsByBookAndReturnedFalse(Book book);

    boolean existsByBookAndUserAndReturnedFalse(Book book, User user);

    Page<Borrow> findByReturnedFalse(Pageable pageable);

    Long countByReturnedFalse();
}
