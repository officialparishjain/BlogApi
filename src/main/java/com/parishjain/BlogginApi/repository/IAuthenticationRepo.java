package com.parishjain.BlogginApi.repository;

import com.parishjain.BlogginApi.models.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {


    AuthenticationToken findFirstByAuthToken(String token);
}
