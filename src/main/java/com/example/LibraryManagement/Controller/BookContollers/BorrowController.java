package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Dto.LibraryReletedDto.BorrowDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.ReturnBookDto;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Service.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("borrow")
@AllArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping()
    public ResponseEntity<List<Borrow>> GetAllBorrows(){
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<String> addBorrow(@RequestBody BorrowDto borrowDto){
        borrowService.addBorrow(borrowDto);
        return ResponseEntity.ok("Successfully Added");
    }

    @PostMapping("returnbook")
    public ResponseEntity<String> returnBook(@RequestBody ReturnBookDto returnBookDto){
         return ResponseEntity.status(HttpStatus.OK).body(borrowService.returnBook(returnBookDto));
    }
}
