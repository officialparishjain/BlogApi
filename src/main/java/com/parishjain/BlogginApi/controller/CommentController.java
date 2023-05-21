package com.parishjain.BlogginApi.controller;

import com.parishjain.BlogginApi.models.BlogComment;
import com.parishjain.BlogginApi.models.BlogUser;
import com.parishjain.BlogginApi.service.AuthenticationService;
import com.parishjain.BlogginApi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/comment")
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add/{email}/{token}")
    ResponseEntity<String> addComment(@PathVariable String email,
                                      @PathVariable String token,
                                      @RequestBody BlogComment comment){

        HttpStatus status;
        String response;
        if(authenticationService.authenticate(email,token)){
            response = commentService.addComment(comment);
            status = HttpStatus.OK;
        }
        else {
            status = HttpStatus.FORBIDDEN;
            response = "Check Token and Email";
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/delete/{commentId}/{email}/{token}")
    ResponseEntity<String> deleteComment(@PathVariable Long commentId,
                                         @PathVariable String email,
                                         @PathVariable String token){
        HttpStatus status;
        String response;
        if(authenticationService.authenticate(email,token)){
            BlogUser user =  authenticationService.findUserByToken(token);
            response = commentService.deleteCommentBtId(user,commentId);
            status = HttpStatus.OK;
        }
        else {
            status = HttpStatus.FORBIDDEN;
            response = "Check Token and Email";
        }
        return ResponseEntity.status(status).body(response);
    }

}
