package com.parishjain.BlogginApi.service;

import com.parishjain.BlogginApi.dto.SignInInputDto;
import com.parishjain.BlogginApi.dto.SignInOutputDto;
import com.parishjain.BlogginApi.dto.SignUpInputDto;
import com.parishjain.BlogginApi.dto.SignUpOutputDto;
import com.parishjain.BlogginApi.models.AuthenticationToken;
import com.parishjain.BlogginApi.models.BlogUser;
import com.parishjain.BlogginApi.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FollowerService followerService;

    @Autowired
    FollowingService followingService;


    public ResponseEntity<SignUpOutputDto> signup(SignUpInputDto signUpInputDto) {

        // FIRST WE WILL CHECK USER IS ALREADY PRESENT IN DATABASE OR NOT
        String email = signUpInputDto.getUserEmail();

        Boolean isPresent = userRepo.existsByUserEmail(email);

        if(isPresent){
            // USER IS ALREADY EXIST
            return ResponseEntity.badRequest().body(
                    new SignUpOutputDto("Failure",
                            "User with this email is already exist..")
            );
        }
        else{

            // NOW THIS MEANS THAT USER IS NOT PRESENT SO WE HAVE TO REGISTER THE USER
            // FIRST WE WILL ENCRYPT THE MESSAGE

            String encryptPassword = null;
            try{
                encryptPassword =  generateEncryptPassword(signUpInputDto.getUserPassword());
            }
            catch (Exception e) {
                e.printStackTrace();
            }


            // Now we have to save Blog user
            BlogUser blogUser = new BlogUser(
                    signUpInputDto.getUserFirstName(),
                    signUpInputDto.getUserLastName(),
                    signUpInputDto.getUserName(),
                    signUpInputDto.getUserMobile(),
                    signUpInputDto.getUserEmail(),
                    signUpInputDto.getUserBio(),
                    encryptPassword,
                    signUpInputDto.getUserDob()
            );

            // Now save
            userRepo.save(blogUser);
            return ResponseEntity.ok().body(new SignUpOutputDto("Success","User Signup successfully.."));

        }


    }

    private String generateEncryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        // Get the user's password as a byte array and update the message digest with it
        messageDigest.update(password.getBytes());
        // Calculate the MD5 hash of the password
        byte[] digested = messageDigest.digest();
        // Concert Byte Array to hexadecimal String
        return DatatypeConverter.printHexBinary(digested);

    }


    public ResponseEntity<SignInOutputDto> signIn(SignInInputDto signInInputDto) {

        // FIRST WE WILL CHECK USER IS ALREADY PRESENT IN DATABASE OR NOT
        String email = signInInputDto.getEmail();
        Boolean isPresent = userRepo.existsByUserEmail(email);

        if(!isPresent){
            return ResponseEntity.badRequest().body(new SignInOutputDto("Failure","User not present.."));
        }

        else{

            String encryptPassword = null;
            try{
                encryptPassword =  generateEncryptPassword(signInInputDto.getPassword());
            }
            catch (Exception ex){
                return ResponseEntity.badRequest().body(
                        new SignInOutputDto("Failure",
                                "Exception occurred "+ ex ));
            }

            // FIRST GET USER FROM EMAIL
            BlogUser blogUser = userRepo.findFirstByUserEmail(signInInputDto.getEmail());

            // CORRESPONDING PASSWORD
            String password = blogUser.getUserPassword();

            // Matching Password
            boolean isValidUser = password.equals(encryptPassword);

            if(isValidUser){

                //  USER  IS A VALID USER THEN WE WILL GENERATE A TOKEN A RETURN TO THE USER
                AuthenticationToken authenticationToken = new AuthenticationToken(blogUser);

                // NOW WE WILL SAVE THE AUTHENTICATION TOKEN
                authenticationService.save(authenticationToken);

                return ResponseEntity.ok().body(new SignInOutputDto(authenticationToken.getAuthToken(),"Success.."));
            }
            else{
                return ResponseEntity.badRequest().body(new SignInOutputDto("Failure","Password Mismatch..."));
            }
        }
    }

    public String follow(String email, Long otherId) {

        BlogUser otherUser = userRepo.findByUserId(otherId);
        BlogUser blogUser = userRepo.findFirstByUserEmail(email);

        Long myId = blogUser.getUserId();
        if(myId == otherId){
            return "Can't follow yourself...";
        }

        if(blogUser != null && otherUser != null){

            // WE WILL NOW START THE PROCEDURE OF FOLLOW

            // FIRST --- FOLLOW FROM MY SIDE
            // I WILL FOLLOW MEANS FOLLOWING WILL INCREASE FORM MY SIDE
            followingService.saveFollowing(blogUser,otherUser);


            // SECOND --- Follower FROM OTHER SIDE
            followerService.saveFollower(otherUser,blogUser);

            return "Followed Successfully!!!!!";
        }
        else
        {
            return "Users are invalid!!!";
        }

    }
}
