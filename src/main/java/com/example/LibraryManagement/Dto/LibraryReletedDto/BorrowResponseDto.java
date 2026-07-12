package com.example.LibraryManagement.Dto.LibraryReletedDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowResponseDto {

    private Long borrowId;

    private String bookName;

    private String author;

    private String category;

    private String memberName;

    private String memberEmail;

    private LocalDate issueDate;

    private LocalDate returnDate;

    private boolean returned;

}
