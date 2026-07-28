package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow.BorrowResponseDto;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Borrow;
import com.example.LibraryManagement.Repo.BookRepo;
import com.example.LibraryManagement.Repo.BorrowRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService{

    private final BorrowRepo borrowRepo;
    private final BookRepo bookRepo;

    public Page<BorrowResponseDto> getBorrowedBooks(int page,int size,String sortBy,String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();


        Pageable pageable= PageRequest.of(page,size,sort);


        Page<Borrow> borrows = borrowRepo.findByReturnedFalse(pageable);

        return borrows.map((borrow)-> new BorrowResponseDto(

                borrow.getId(),
                borrow.getBook().getBookName(),
                borrow.getUser().getName(),
                borrow.getUser().getEmail(),
                borrow.getUser().getMobileNumber(),
                borrow.getIssueDate(),
                borrow.getReturnDate(),
                borrow.isReturned()));
    }

    public Page<BookResponseDto> getAvailableBooks(int page,int size,String sortBy,String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();


        Pageable pageable= PageRequest.of(page,size,sort);

        Page<Book> bookList = bookRepo.findByAvailableCopiesGreaterThan(0,pageable);

        return bookList.map((book)-> new BookResponseDto(
                book.getId(),
                book.getBookName(),
                book.getImageUrl(),
                book.getCategory().getName(),
                book.getAuthor().getName(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        ));
    }
}