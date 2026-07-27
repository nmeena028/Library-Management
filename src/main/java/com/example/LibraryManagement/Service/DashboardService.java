package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LibraryReletedDto.DashBoardResponseDto;
import com.example.LibraryManagement.Repo.*;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final BookRepo bookRepo;
    private final BorrowRepo borrowRepo;
    private final UserRepo userRepo;
    private final AuthorRepo authorRepo;
    private final CategoryRepo categoryRepo;

    public DashBoardResponseDto getDashBoard() {

        return new DashBoardResponseDto(
                bookRepo.count(),
                bookRepo.countByAvailableCopiesGreaterThan(0),
                borrowRepo.countByReturnedFalse(),
                userRepo.count(),
                authorRepo.count(),
                categoryRepo.count()
        );
    }
}
