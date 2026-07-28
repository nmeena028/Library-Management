package com.example.LibraryManagement.OAuth2;


import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Repo.UserRepo;
import com.example.LibraryManagement.Security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler
        implements AuthenticationSuccessHandler {




    @Autowired
    private final JwtUtil jwtUtil;

    private final UserRepo userRepo;


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException {


        OAuth2AuthenticationToken token =
                (OAuth2AuthenticationToken) authentication;


        OidcUser oidcUser =
                (OidcUser) authentication.getPrincipal();


       User user= userRepo.findByEmail(oidcUser.getEmail()).orElseThrow();


        String jwt =
                jwtUtil.generateToken(user);


        response.sendRedirect("http://localhost:5173/login?token=" + jwt);


    }

}
