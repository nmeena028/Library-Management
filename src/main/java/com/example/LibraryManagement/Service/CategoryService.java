package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.CategoryDto;
import com.example.LibraryManagement.Entity.Category;
import com.example.LibraryManagement.Repo.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }


    public void addCategory(CategoryDto categoryDto) {
        if(categoryRepo.existsByName(categoryDto.getName())){
            throw new RuntimeException("Category already exists");
        }
        Category category=new Category();
        category.setName(categoryDto.getName());
        categoryRepo.save(category);
    }
}
