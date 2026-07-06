package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Repo.BookRepo;
import com.example.LibraryManagement.Repo.BorrowRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService{

    private final BorrowRepo borrowRepo;
    private final BookRepo bookRepo;

    public List<Borrow> getBorrowedBooks() {
        return borrowRepo.findByReturnedFalse();
    }

    public List<Book> getAvailableBooks() {
        return bookRepo.findByAvailableCopiesGreaterThan(0);
    }
}