//package com.example.LibraryManagement.Controller;
//
//import com.example.LibraryManagement.Dto.Authentication.RegisterDto;
//import com.example.LibraryManagement.Entity.User;
//import com.example.LibraryManagement.Service.AuthenticationService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("customer")
//@AllArgsConstructor
//public class UserController {
//    private final AuthenticationService authenticationService;
//
//    @GetMapping()
//    public ResponseEntity<List<User>> GetAllCategory(){
//        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.findAll());
//    }
//
//    @PostMapping()
//    public ResponseEntity<String> AddCategory(@RequestBody RegisterDto user){
//        authenticationService.registerMethod(user);
//        return ResponseEntity.ok("Successfully Added");
//    }
//}
