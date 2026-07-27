package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Entity.Category;
import com.example.LibraryManagement.Repo.BookRepo;
import com.example.LibraryManagement.Repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilterSearchService {

    private final CategoryRepo categoryRepo;
    private final BookRepo bookRepo;
    public Page<BookResponseDto> findAllBooks(
                                               String categoryName,
                                               int page,
                                               int size,
                                               String sortBy,
                                               String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);


        Category category= categoryRepo.findByName(categoryName.toUpperCase()).orElseThrow(()->
                new RuntimeException("Category Name is Wrong"));

        Page<Book> bookPage = bookRepo.findByCategory(category, pageable);

        return bookPage.map(book -> new BookResponseDto(
                book.getBookName(),
                book.getImageUrl(),
                book.getCategory().getName(),
                book.getAuthor().getName(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        ));

    }
}
