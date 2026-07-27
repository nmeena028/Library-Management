package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Dto.LibraryReletedDto.CategoryRequestDto;
import com.example.LibraryManagement.Entity.Category;
import com.example.LibraryManagement.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Category>> GetAllCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name")String sortBy,
            @RequestParam(defaultValue = "asc")String direction){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll(page,size,sortBy,direction));
    }

    @PostMapping()
    public ResponseEntity<String> AddCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        categoryService.addCategory(categoryRequestDto);
        return ResponseEntity.ok("Successfully Added");
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<String> DeleteByID(@PathVariable Long Id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteById(Id));
    }
}
