package com.example.LibraryManagement.Dto.Authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedNotifications;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @Size(min = 10 ,max =10)
    private String mobileNumber;

    @NotBlank
    private String password;
}
