package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.BookDto;
import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Category;
import com.example.LibraryManagement.Repo.AuthorRepo;
import com.example.LibraryManagement.Repo.BookRepo;
import com.example.LibraryManagement.Repo.BorrowRepo;
import com.example.LibraryManagement.Repo.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final AuthorRepo authorRepo;
    private final BorrowRepo borrowRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public void addBook(Book book) {
        if(categoryRepo.existsById(book.getCategory().getId()) && authorRepo.existsById(book.getAuthor().getId())){
            bookRepo.save(book);
        }
    }

    public void updateBook(BookDto book,Long id) {
       Book book1 =bookRepo.findById(id).orElseThrow();
       book1.setBookName(book.getBookName());
       Category cg= categoryRepo.findById(book.getCategoryId()).orElseThrow();
       Author author= authorRepo.findById(book.getAuthorId()).orElseThrow();
       book1.setCategory(cg);
       book1.setAuthor(author);
       book1.setTotalCopies(book.getTotalCopies());
       bookRepo.save(book1);
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
