package com.parishjain.BlogginApi.service;

import com.parishjain.BlogginApi.models.BlogComment;
import com.parishjain.BlogginApi.models.BlogUser;
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

    public String deleteCommentBtId(BlogUser user, Long commentId) {

        boolean isPresent = commentRepo.existsById(commentId);
        if(isPresent){
            commentRepo.deleteById(commentId);
            return "Deleted";
        }
        else{
            return "Comment with id does not present ..";
        }


    }
}
