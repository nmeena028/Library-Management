package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.CategoryRequestDto;
import com.example.LibraryManagement.Entity.Category;
import com.example.LibraryManagement.Repo.CategoryRepo;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public Page<Category> findAll(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        return categoryRepo.findAll(pageable);
    }


    public void addCategory(CategoryRequestDto categoryRequestDto) {
        if(categoryRepo.existsByName(categoryRequestDto.getName())){
            throw new RuntimeException("Category already exists");
        }
        Category category=new Category();
        category.setName(categoryRequestDto.getName().toUpperCase());
        categoryRepo.save(category);
    }

    public String deleteById(Long id) {
        if (!categoryRepo.existsById(id)){
            throw  new RuntimeException("Id is Not Exist");
        }
        categoryRepo.deleteById(id);
        return "Category Deleted Successfully";
    }
}
