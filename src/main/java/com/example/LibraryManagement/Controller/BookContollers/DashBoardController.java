package com.example.LibraryManagement.Controller.BookContollers;


import com.example.LibraryManagement.Dto.LibraryReletedDto.DashBoardResponseDto;
import com.example.LibraryManagement.Service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/dashboard")
public class DashBoardController {

    private final DashboardService dashboardService;

    @GetMapping("")
    public ResponseEntity<DashBoardResponseDto> getDashboard(){
        return ResponseEntity.status(HttpStatus.OK).body(dashboardService.getDashBoard());
    }
}
