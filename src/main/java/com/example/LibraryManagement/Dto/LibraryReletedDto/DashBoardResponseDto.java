package com.example.LibraryManagement.Dto.LibraryReletedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardResponseDto {

    private Long totalBooks;
    private Long availableBooks;
    private Long borrowedBooks;
    private Long totalUsers;
    private Long totalAuthors;
    private Long totalCategories;
}
