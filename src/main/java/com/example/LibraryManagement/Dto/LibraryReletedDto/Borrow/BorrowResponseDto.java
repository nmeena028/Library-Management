package com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowResponseDto {

    private Long borrowId;

    private String bookName;

    private String memberName;

    private String memberEmail;

    private String memberNumber;

    private LocalDate issueDate;

    private LocalDate returnDate;

    private boolean returned;

}
