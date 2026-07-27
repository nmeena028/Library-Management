package com.example.LibraryManagement.Dto.LibraryReletedDto.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {

    private String bookName;

    private String imageUrl;

    private Long categoryId;

    private Long authorId;

    private Integer totalCopies;

}