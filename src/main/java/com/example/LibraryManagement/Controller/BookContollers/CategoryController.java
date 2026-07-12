package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Dto.LibraryReletedDto.CategoryDto;
import com.example.LibraryManagement.Entity.Category;
import com.example.LibraryManagement.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> GetAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> AddCategory(@RequestBody CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return ResponseEntity.ok("Successfully Added");
    }
}
