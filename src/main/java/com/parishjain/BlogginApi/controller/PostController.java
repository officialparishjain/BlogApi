package com.parishjain.BlogginApi.controller;

import com.parishjain.BlogginApi.models.BlogPost;
import com.parishjain.BlogginApi.models.BlogUser;
import com.parishjain.BlogginApi.service.AuthenticationService;
import com.parishjain.BlogginApi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    AuthenticationService authService;

    @Autowired
    PostService postService;

    // CREATE POST
    @PostMapping("/add/{email}/{token}")
    public ResponseEntity<String> addPost(@Valid @PathVariable String email , @PathVariable String token , @RequestBody BlogPost post){
        HttpStatus status;
        String msg = "";
        if(authService.authenticate(email,token))
        {
            BlogUser user =  authService.findUserByToken(token);
            post.setBlogUser(user);
            postService.addPost(post);
            msg = " Post posted successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }


    // GET ALL POSTS
    @GetMapping("/all/{email}/{token}")
    ResponseEntity<List<BlogPost>> getAllPost(@PathVariable String email, @PathVariable String token){

        HttpStatus status;
        List<BlogPost> postList = null;
        if(authService.authenticate(email,token)){
            postList = postService.fetchAllPost(email);
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(postList, status);
    }


    // UPDATE POST DATA
    @PutMapping("/update/{postId}/{data}/{email}/{token}")
    ResponseEntity<String> updatePostData(@PathVariable Long postId,
                                          @PathVariable String data,
                                          @PathVariable String email,
                                          @PathVariable String token){

        HttpStatus status;
        String response;
        if(authService.authenticate(email,token)){
            // NOW HE/SHE CAN UPDATE THE POST
            BlogUser user =  authService.findUserByToken(token);
            status = HttpStatus.OK;
            response = postService.updatePostData(user,postId,data);
        }
        else{
            status = HttpStatus.FORBIDDEN;
            response = "Something went wrong";
        }
        return ResponseEntity.status(status).body(response);
    }

    // DELETE POST BY ID
    @DeleteMapping("/delete/{postId}/{email}/{token}")
    ResponseEntity<String> deletePostById(@PathVariable Long postId,
                                          @PathVariable String email,
                                          @PathVariable String token){
        HttpStatus status;
        String response;
        if(authService.authenticate(email,token)){
            BlogUser user =  authService.findUserByToken(token);
            response = postService.deletePostById(user,postId);
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
            response = "Something went wrong";
        }
        return ResponseEntity.status(status).body(response);
    }



}
