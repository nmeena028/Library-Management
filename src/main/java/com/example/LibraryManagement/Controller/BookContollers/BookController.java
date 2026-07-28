package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookRequestDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookRequestDto;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<BookResponseDto>> GetAllBooks(
            @RequestParam(defaultValue ="0")int page,
            @RequestParam(defaultValue ="10")int size,
            @RequestParam(defaultValue = "bookName")String sortBy,
            @RequestParam(defaultValue = "asc")String direction){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks(page,size,sortBy,direction));
    }




    @PostMapping()
    public ResponseEntity<String> AddBook(@RequestBody BookRequestDto book){

        return ResponseEntity.status(HttpStatus.OK).body(bookService.addBook(book));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getById(id));

    }

    @PutMapping("{id}")
    public ResponseEntity<BookResponseDto> update_Book(@PathVariable Long id ,@RequestBody BookRequestDto book){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(book,id));

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

    @GetMapping("/search")
    public ResponseEntity<Page<BookResponseDto>> searchBook(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){

        return ResponseEntity.ok(
                bookService.searchBook(keyword,page,size,sortBy,direction)
        );
    }
}
