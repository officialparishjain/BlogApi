package com.parishjain.BlogginApi.service;

import com.parishjain.BlogginApi.models.BlogComment;
import com.parishjain.BlogginApi.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    public String addComment(BlogComment comment) {
        BlogComment blogComment =  commentRepo.save(comment);
        return "Comment Saved Successfully...";
    }
}
