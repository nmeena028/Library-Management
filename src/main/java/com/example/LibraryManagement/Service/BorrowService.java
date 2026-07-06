package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.BorrowDto;
import com.example.LibraryManagement.Dto.ReturnBookDto;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Entity.Customer;
import com.example.LibraryManagement.Repo.BookRepo;
import com.example.LibraryManagement.Repo.BorrowRepo;
import com.example.LibraryManagement.Repo.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowService {

    private final BorrowRepo borrowRepo;
    private final BookRepo bookRepo;
    private final CustomerRepo customerRepo;

    public List<Borrow> findAll() {
        return borrowRepo.findAll();
    }

    public void addBorrow(BorrowDto borrowDto) {

        Book book = bookRepo.findById(borrowDto.getBookId()).orElseThrow();
        Customer customer = customerRepo.findById(borrowDto.getCustomerId()).orElseThrow();

        // NEW RULE: duplicate borrow check
        if (borrowRepo.existsByBookAndCustomerAndReturnedFalse(book, customer)) {
            throw new RuntimeException("Customer already borrowed this book and not returned yet");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setCustomer(customer);
        borrow.setIssueDate(LocalDate.now());
        borrow.setReturned(false);

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        bookRepo.save(book);
        borrowRepo.save(borrow);
    }

    public String returnBook(ReturnBookDto returnBookDto) {
      Borrow borrow=borrowRepo.findById(returnBookDto.getBorrowId()).orElseThrow();
        if (borrow.isReturned()) {
            throw new RuntimeException("Book already returned");
        }
      Book book=borrow.getBook();
      book.setAvailableCopies(book.getAvailableCopies()+1);
      borrow.setReturnDate(LocalDate.now());
      borrow.setReturned(true);
      borrowRepo.save(borrow);
      bookRepo.save(book);
      return "Book returned successfully";
    }
}
