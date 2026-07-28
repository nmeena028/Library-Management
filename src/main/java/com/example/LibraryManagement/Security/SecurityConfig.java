package com.example.LibraryManagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import com.example.LibraryManagement.OAuth2.GoogleOidcUserService;
import com.example.LibraryManagement.OAuth2.OAuth2SuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityConfig {

    private final JwtSecurityFilterChain jwtSecurityFilterChain;

    private final OAuth2SuccessHandler oauth2SuccessHandler;

    private  final GoogleOidcUserService customOidcUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https){
        return https.cors(Customizer.withDefaults())   // <-- Ye add kar
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/oauth2/**",
                                        "/login/**"
                                ).permitAll()
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
                .oauth2Login(oauth -> oauth

                        .userInfoEndpoint(user ->

                                user.oidcUserService(
                                        customOidcUserService
                                )

                        )

                        .successHandler(
                                oauth2SuccessHandler
                        )

                )
                .addFilterBefore(
                         jwtSecurityFilterChain, UsernamePasswordAuthenticationFilter.class
                ).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
