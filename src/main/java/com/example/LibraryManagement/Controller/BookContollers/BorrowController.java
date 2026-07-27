package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow.BorrowRequestDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow.BorrowResponseDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.ReturnBookDto;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Service.BorrowService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<BorrowResponseDto>> GetAllBorrows(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "issueDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.findAll(page,size,sortBy,direction));
    }

    @PostMapping("")
    public ResponseEntity<String> addBorrow(@RequestBody BorrowRequestDto borrowDto){
        borrowService.addBorrow(borrowDto);
        return ResponseEntity.ok("Successfully Added");
    }

    @PostMapping("returnbook")
    public ResponseEntity<String> returnBook(@RequestBody ReturnBookDto returnBookDto){
         return ResponseEntity.status(HttpStatus.OK).body(borrowService.returnBook(returnBookDto));
    }
}
