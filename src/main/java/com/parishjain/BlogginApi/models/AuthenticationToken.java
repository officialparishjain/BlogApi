package com.parishjain.BlogginApi.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authTokenId;
    private String authToken;
    private LocalDate authTokenDate;
    @OneToOne
    @JoinColumn(nullable = false,name = "fk_user_id")
    private BlogUser authBlogUser;


    public AuthenticationToken( BlogUser authInstaUser) {
        this.authToken = UUID.randomUUID().toString();
        this.authTokenDate = LocalDate.now();
        this.authBlogUser = authInstaUser;
    }

}
