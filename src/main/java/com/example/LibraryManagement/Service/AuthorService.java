package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Entity.Author;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Repo.AuthorRepo;
import com.example.LibraryManagement.Repo.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;


    public Page<Author> findAll(int page,int size,String sortBy,String direction) {

        Sort sort=direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);

        return authorRepo.findAll(pageable);
    }


    public void addAuthor(Author author) {
        authorRepo.save(author);
    }

    public Page<BookResponseDto> getAllBook(Long id,
                                            int page,
                                            int size,
                                            String sortBy,
                                            String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Book> bookPage = bookRepo.findByAuthorId(id,pageable);

        return bookPage.map(book -> new BookResponseDto(
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
