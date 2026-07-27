package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow.BorrowResponseDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow.BorrowRequestDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.ReturnBookDto;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Repo.BookRepo;
import com.example.LibraryManagement.Repo.BorrowRepo;
import com.example.LibraryManagement.Repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowService {

    private final BorrowRepo borrowRepo;
    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    public Page<BorrowResponseDto> findAll(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(page,size,sort);


        Page<Borrow> borrowList= borrowRepo.findAll(pageable);

        return borrowList.map((borrow) -> new BorrowResponseDto(
                borrow.getId(),
                borrow.getBook().getBookName(),
                borrow.getUser().getName(),
                borrow.getUser().getEmail(),
                borrow.getUser().getMobileNumber(),
                borrow.getIssueDate(),
                borrow.getReturnDate(),
                borrow.isReturned())
        );
    }

    public void addBorrow(BorrowRequestDto borrowDto) {

        Book book = bookRepo.findById(borrowDto.getBookId()).orElseThrow();
        User user = userRepo.findById(borrowDto.getUserId()).orElseThrow();

        // NEW RULE: duplicate borrow check
        if (borrowRepo.existsByBookAndUserAndReturnedFalse(book, user)) {
            throw new RuntimeException("User already borrowed this book and not returned yet");
        }
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(user);
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
