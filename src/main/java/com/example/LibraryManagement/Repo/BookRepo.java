package com.example.LibraryManagement.Repo;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

    Page<Book> findByAvailableCopiesGreaterThan(int i, Pageable pageable);

    Page<Book> findByCategory(Category category, Pageable pageable);

    Page<Book> findByAuthorId(Long id, Pageable pageable);

    Long countByAvailableCopiesGreaterThan(int i);

    Page<Book> findByBookNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );
}
