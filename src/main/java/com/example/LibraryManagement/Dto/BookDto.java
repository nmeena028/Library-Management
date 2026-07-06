package com.example.LibraryManagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String bookName;

    private Long categoryId;

    private Long authorId;

    private Integer totalCopies;

}