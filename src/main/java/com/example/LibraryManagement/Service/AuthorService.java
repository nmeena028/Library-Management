package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Repo.AuthorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;


    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    public void addAuthor(Author author) {
        authorRepo.save(author);
    }

    public List<Book> getAllBook(Long id) {
        Author author= authorRepo.findById(id).orElseThrow();
        return author.getBooks();
    }
}
