package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping()
    public ResponseEntity<Page<Author>> getAllAuthor(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "name")String sortBy,
            @RequestParam(defaultValue = "asc")String direction){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.findAll(page,size,sortBy,direction));
    }

    @PostMapping()
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return ResponseEntity.ok("Successfully Added");
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Page<BookResponseDto>> getAllBooks(@PathVariable Long id,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "bookName") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String direction){

        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAllBook(id,page,size,sortBy,direction));
    }

}
