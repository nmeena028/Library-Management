package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping()
    public ResponseEntity<List<Author>> getAllAuthor(){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return ResponseEntity.ok("Successfully Added");
    }

    @GetMapping("{id}/books")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAllBook(id));
    }

}
