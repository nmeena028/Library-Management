package com.example.LibraryManagement.Controller.BookContollers;


import com.example.LibraryManagement.Dto.LibraryReletedDto.Book.BookResponseDto;
import com.example.LibraryManagement.Service.FilterSearchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@AllArgsConstructor
public class FilterSearchController {

    private final FilterSearchService filterSearchService;

    @GetMapping("/{cname}")
    public ResponseEntity<Page<BookResponseDto>> GetAllBooks(
            @PathVariable String cname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction){
        return ResponseEntity.status(HttpStatus.OK).body(filterSearchService.findAllBooks(cname, page, size, sortBy, direction));

    }


}
