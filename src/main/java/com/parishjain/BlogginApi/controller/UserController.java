package com.parishjain.BlogginApi.controller;

import com.parishjain.BlogginApi.dto.SignInInputDto;
import com.parishjain.BlogginApi.dto.SignInOutputDto;
import com.parishjain.BlogginApi.dto.SignUpInputDto;
import com.parishjain.BlogginApi.dto.SignUpOutputDto;
import com.parishjain.BlogginApi.service.AuthenticationService;
import com.parishjain.BlogginApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    // User Sign Up
    @PostMapping("/signup")
    private ResponseEntity<SignUpOutputDto> signup(@RequestBody SignUpInputDto signUpInputDto){
        return userService.signup(signUpInputDto);
    }

    // SIGN IN USER TO INSTAGRAM

    @PostMapping("/signin")
    private ResponseEntity<SignInOutputDto> signIn(@RequestBody SignInInputDto signInInputDto){
        return userService.signIn(signInInputDto);
    }


    @PostMapping(value = "/follow/{email}/{token}/{otherId}")
    private ResponseEntity<String> followUser(@PathVariable String email,
                                              @PathVariable String token,
                                              @PathVariable Long otherId){

        // FIRST WE WILL DO AUTHENTICATION THAT USER IS VALID OR NOT

        String response;
        HttpStatus status;
        if(authenticationService.authenticate(email,token)){
            try{
                response = userService.follow(email,otherId);
                status = HttpStatus.OK;
            }
            catch (Exception ex){
                response = "Exception occurred : " + ex;
                status = HttpStatus.BAD_REQUEST;
            }
        }
        else {
            response = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }
        return ResponseEntity.status(status).body(response);
    }
}
