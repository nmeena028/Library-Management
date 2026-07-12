package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.Authentication.LoginRequestDto;
import com.example.LibraryManagement.Dto.Authentication.LoginResponse;
import com.example.LibraryManagement.Dto.Authentication.RegisterDto;
import com.example.LibraryManagement.Entity.Roles;
import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Repo.UserRepo;
import com.example.LibraryManagement.Security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Transactional
    public void registerMethod(RegisterDto user) {

        if(userRepo.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        User u1=new User();
        u1.setName(user.getName());
        u1.setEmail(user.getEmail());
        u1.setPassword(passwordEncoder.encode(user.getPassword()));
        u1.setRole(Roles.MEMBER);

        userRepo.save(u1);
    }


    public  LoginResponse loginMethod(LoginRequestDto loginRequestDto) {
        User user = userRepo.findByEmail(loginRequestDto.getEmail()).orElseThrow(()->new RuntimeException("User Not found"));
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println(passwordEncoder.encode("12345"));
        System.out.println("\n\n\n\n\n\n\n");
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw  new RuntimeException("User Password is Wrong");
        }
        return new LoginResponse(jwtUtil.generateToken(user));
    }
}
