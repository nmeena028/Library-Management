package com.example.LibraryManagement.Controller.BookContollers;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/borrowed-books")
    public ResponseEntity<List<Borrow>> getBorrowedBooks() {
        return ResponseEntity.ok(reportService.getBorrowedBooks());
    }

    @GetMapping("/available-books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(reportService.getAvailableBooks());
    }
}
