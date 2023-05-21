package com.parishjain.BlogginApi.service;

import com.parishjain.BlogginApi.models.BlogFollower;
import com.parishjain.BlogginApi.models.BlogUser;
import com.parishjain.BlogginApi.repository.IFollowerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {

    @Autowired
    IFollowerRepo followerRepo;
    public void saveFollower(BlogUser myUser, BlogUser otherUser ) {

        BlogFollower blogFollower = new BlogFollower(null,myUser,otherUser);
        followerRepo.save(blogFollower);
    }
}
