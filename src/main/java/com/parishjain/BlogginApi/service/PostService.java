package com.parishjain.BlogginApi.service;

import com.parishjain.BlogginApi.models.BlogPost;
import com.parishjain.BlogginApi.models.BlogUser;
import com.parishjain.BlogginApi.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public void addPost(BlogPost post) {
        postRepo.save(post);
    }

    public List<BlogPost> fetchAllPost(String email) {
        return postRepo.findAll();
    }

    @Transactional
    @Modifying
    public String updatePostData(BlogUser user, Long postId, String data) {

        BlogPost blogPost = postRepo.findById(postId).get();
        BlogUser blogUser = blogPost.getBlogUser();

        if(blogUser.getUserId() == user.getUserId()){
            blogPost.setPostData(data);
            return "Post has been updated";
        }
        else{
            return "You are not authorized to update this post...";
        }


    }

    @Modifying
    public String deletePostById(BlogUser user, Long postId) {
        BlogPost blogPost = postRepo.findById(postId).get();
        BlogUser blogUser = blogPost.getBlogUser();

        if(blogUser.getUserId() == user.getUserId()){
            postRepo.deleteById(postId);
            return "Post Deleted Successfully...";
        }
        else{
            return "You are not authorized to delete this post...";
        }
    }
}
