package com.example.LibraryManagement.Repo;

import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {
    boolean existsByName(String authorName);

    Optional<Author> findByName(String authorName);

}
