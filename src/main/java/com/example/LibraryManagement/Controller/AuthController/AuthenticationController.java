package com.example.LibraryManagement.Controller.AuthController;


import com.example.LibraryManagement.Dto.Authentication.LoginRequestDto;
import com.example.LibraryManagement.Dto.Authentication.LoginResponse;
import com.example.LibraryManagement.Dto.Authentication.RegisterDto;
import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        authenticationService.registerMethod(registerDto);
        return ResponseEntity.ok("Successfully Added");
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto){
        System.out.println("\n \n \n "+loginRequestDto.getEmail());
        System.out.println("\n \n \n "+loginRequestDto.getPassword()+"\n \n \n ");
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.loginMethod(loginRequestDto));
    }


}
