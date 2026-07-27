package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookRequestDto;
import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Category;
import com.example.LibraryManagement.Repo.AuthorRepo;
import com.example.LibraryManagement.Repo.BookRepo;
import com.example.LibraryManagement.Repo.BorrowRepo;
import com.example.LibraryManagement.Repo.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final AuthorRepo authorRepo;
    private final BorrowRepo borrowRepo;

    public Page<BookResponseDto> findAllBooks(int page,int size,String sortBy,String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Book> bookList= bookRepo.findAll(pageable);

        return bookList.map((book)-> new BookResponseDto(
                book.getBookName(),
                book.getImageUrl(),
                book.getCategory().getName(),
                book.getAuthor().getName(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        ));

    }

    public String addBook(BookRequestDto bookDto) {
            Category category=categoryRepo.findById(bookDto.getCategoryId()).orElseThrow(()->
                    new RuntimeException("Category ID is Incorrect"));
            Author author=authorRepo.findById(bookDto.getAuthorId()).orElseThrow(()->
                    new RuntimeException("Author ID is Incorrect"));

            Book book= new Book();
            book.setBookName(bookDto.getBookName());
            book.setImageUrl(bookDto.getImageUrl());
            book.setCategory(category);
            book.setAuthor(author);
            book.setTotalCopies(bookDto.getTotalCopies());
            book.setAvailableCopies(bookDto.getTotalCopies());

            bookRepo.save(book);

            return "Book Added Successfully";
    }

    public BookResponseDto updateBook(BookRequestDto book, Long id) {
       Book book1 =bookRepo.findById(id).orElseThrow();
       book1.setBookName(book.getBookName());
       book1.setImageUrl(book.getImageUrl());
       Category cg= categoryRepo.findById(book.getCategoryId()).orElseThrow(()->
               new RuntimeException("Category ID is Incorrect"));
       Author author= authorRepo.findById(book.getAuthorId()).orElseThrow(()->
               new RuntimeException("Author ID is Incorrect"));

       book1.setCategory(cg);
       book1.setAuthor(author);
       bookRepo.save(book1);

       return  new BookResponseDto(
               book1.getBookName(),
               book1.getImageUrl(),
               book1.getCategory().getName(),
               book1.getAuthor().getName(),
               book1.getTotalCopies(),
               book1.getAvailableCopies());
    }


    public void deleteBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow();
        if (borrowRepo.existsByBookAndReturnedFalse(book)) {
            throw new RuntimeException("Book is currently borrowed. Cannot delete.");
        }
        bookRepo.delete(book);
    }

    public void addCopies(Long id, int copies) {
        if (copies <= 0) {
            throw new RuntimeException("Copies must be greater than 0");
        }
        Book book=bookRepo.findById(id).orElseThrow();
        book.setTotalCopies(book.getTotalCopies()+copies);
        book.setAvailableCopies(book.getAvailableCopies()+copies);
        bookRepo.save(book);
    }
}
