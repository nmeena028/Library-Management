package com.example.LibraryManagement.Repo;

import com.example.LibraryManagement.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    boolean existsByName(String Name);

    Optional<Category> findByName(String categoryName);
}
