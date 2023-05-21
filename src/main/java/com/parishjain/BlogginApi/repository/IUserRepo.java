package com.parishjain.BlogginApi.repository;


import com.parishjain.BlogginApi.models.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<BlogUser,Long> {

    Boolean existsByUserEmail(String email);

    BlogUser findFirstByUserEmail(String email);

    BlogUser findByUserId(Long otherId);
}
