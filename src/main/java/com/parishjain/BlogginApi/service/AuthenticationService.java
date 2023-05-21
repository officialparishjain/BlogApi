package com.parishjain.BlogginApi.service;

import com.parishjain.BlogginApi.models.AuthenticationToken;
import com.parishjain.BlogginApi.models.BlogUser;
import com.parishjain.BlogginApi.repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo authenticationRepo;

    public boolean authenticate(String email, String token) {

        // CHECK IF EMAIL OR TOKEN IS NULL OR NOT
        if(email == null || token == null){
            return false;
        }

        AuthenticationToken authToken = authenticationRepo.findFirstByAuthToken(token);

        if(authToken == null) return false;

        String authEmail = authToken.getAuthBlogUser().getUserEmail();

        return authEmail.equals(email);
    }

    public void save(AuthenticationToken authenticationToken) {
        authenticationRepo.save(authenticationToken);
    }

    public BlogUser findUserByToken(String token) {
        return authenticationRepo.findFirstByAuthToken(token).getAuthBlogUser();
    }
}
