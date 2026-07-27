package com.example.LibraryManagement.Dto.LibraryReletedDto.Borrow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequestDto{

    private Long userId;

    private Long bookId;

}