package com.parishjain.BlogginApi.repository;

import com.parishjain.BlogginApi.models.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<BlogComment,Long> {

}
