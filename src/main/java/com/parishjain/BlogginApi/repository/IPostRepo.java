package com.parishjain.BlogginApi.repository;

import com.parishjain.BlogginApi.models.BlogPost;
import com.parishjain.BlogginApi.models.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<BlogPost,Long> {




}
