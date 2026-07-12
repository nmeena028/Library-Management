package com.example.LibraryManagement.Dto.LibraryReletedDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto{

    private Long userId;

    private Long bookId;

}