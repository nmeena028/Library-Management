package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Dto.BookDto;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> GetAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> AddCategory(@RequestBody Book book){
        bookService.addBook(book);
        return ResponseEntity.ok("Successfully Added");
    }
    @PutMapping("{id}")
    public ResponseEntity<String> update_Book(@PathVariable Long id ,@RequestBody BookDto book){
        bookService.updateBook(book,id);
        return ResponseEntity.ok("Update Successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete_Book(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Delete Successfully");

    }
    @PutMapping("{id}/add-copies")
    public ResponseEntity<String> addCopies(@PathVariable Long id, @RequestParam int copies) {
        bookService.addCopies(id, copies);
        return ResponseEntity.ok("Copies increased successfully");
    }
}
