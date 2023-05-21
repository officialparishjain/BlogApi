package com.parishjain.BlogginApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpInputDto {

    @NotNull(message = "User First Name Cannot Be Null...")
    @NotBlank(message = "User First Name Cannot be Blank...")
    @Column(nullable = false)
    private String userFirstName;

    @NotBlank(message = "User Last Name Cannot be Blank...")
    private String userLastName;

    @NotNull(message = "Username cannot be null...")
    @NotBlank(message = "Username cannot be blank ...")
    @Column(unique = true,nullable = false)
    private String userName;

    @Pattern(regexp = "^\\d{2}-\\d{10}$",message = "Must be of 10 digit followed by 2 digit country code. like 91-9999999999")
    @Column(nullable = false)
    private String userMobile;

    @Email(message = "It must follow proper format abc@xyz.com ...")
    @NotNull(message = "It must not be null...")
    @Column(nullable = false)
    private String userEmail;


    private String userBio;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String userPassword;

    @NotNull(message = "User DOB Cannot be null...")
    private LocalDate userDob;
}
