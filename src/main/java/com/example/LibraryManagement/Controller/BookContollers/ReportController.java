package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow.BorrowResponseDto;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Service.ReportService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/borrowed-books")
    public ResponseEntity<Page<BorrowResponseDto>> getBorrowedBooks( @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size,
                                                                     @RequestParam(defaultValue = "issueDate") String sortBy,
                                                                     @RequestParam(defaultValue = "asc")String direction) {

        return ResponseEntity.ok(reportService.getBorrowedBooks(page,size,sortBy,direction));

    }


    @GetMapping("/available-books")
    public ResponseEntity<Page<BookResponseDto>> getAvailableBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookName") String sortBy,
            @RequestParam(defaultValue = "asc")String direction) {

        return ResponseEntity.ok(reportService.getAvailableBooks(page,size,sortBy,direction));
    }
}
