package com.example.LibraryManagement.Security;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityConfig {

    private final JwtSecurityFilterChain jwtSecurityFilterChain;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https){
        return https.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        // ==========================
                        //  Books role
                        // ==========================
                        .requestMatchers(HttpMethod.GET,"/book/**")
                        .hasAnyRole("ADMIN","MEMBER")
                        .requestMatchers(HttpMethod.POST,"/book/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/book/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/book/**").hasRole("ADMIN")
                        // ==========================
                        //  Category Role
                        // ==========================
                        .requestMatchers(HttpMethod.GET,"/category/**")
                        .hasAnyRole("ADMIN","MEMBER")
                        .requestMatchers(HttpMethod.POST,"/category/**").hasRole("ADMIN")

                         // ==========================
                         //  Authors (Book Writers)
                         // ==========================

                        .requestMatchers(HttpMethod.GET,"/authors/**")
                        .hasAnyRole("ADMIN","MEMBER")
                        .requestMatchers(HttpMethod.POST,"/authors/**").hasRole("ADMIN")

                        // ==========================
                        //   Borrow
                        // ==========================
                        .requestMatchers(HttpMethod.POST, "/borrow")
                        .hasAnyRole("ADMIN", "MEMBER")

                        .requestMatchers(HttpMethod.POST, "/borrow/returnbook")
                        .hasAnyRole("ADMIN", "MEMBER")

                        .requestMatchers(HttpMethod.GET, "/borrow/**")
                        .hasRole("ADMIN")

                        // ==========================
                        // Reports
                        // ==========================
                        .requestMatchers("/reports/**")
                        .hasRole("ADMIN")

                        // ==========================
                        //  Everything Else
                        // ==========================
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                         jwtSecurityFilterChain, UsernamePasswordAuthenticationFilter.class
                ).build();
    }
}
