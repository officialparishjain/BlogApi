package com.parishjain.BlogginApi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "User First Name Cannot Be Null...")
    @NotBlank(message = "User First Name Cannot be Blank...")
    @Column(nullable = false)
    private String userFirstName;

    @NotBlank(message = "User Last Name Cannot be Blank...")
    private String userLastName;

    // This is the name of user blog account name it should be unique
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

    private LocalDate userRegisterDate;

    public BlogUser(String userFirstName, String userLastName, String userName, String userMobile, String userEmail, String userBio, String userPassword, LocalDate userDob) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.userBio = userBio;
        this.userPassword = userPassword;
        this.userDob = userDob;
        this.userRegisterDate = LocalDate.now();
    }

}
