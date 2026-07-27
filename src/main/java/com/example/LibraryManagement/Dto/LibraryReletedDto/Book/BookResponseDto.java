package com.example.LibraryManagement.Dto.LibraryReletedDto.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookResponseDto {

    private String bookName;

    private String imageUrl;

    private String categoryName;

    private String authorName;

    private Integer totalCopies;

    private Integer availableCopies;
}
