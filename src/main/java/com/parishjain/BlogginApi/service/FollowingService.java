package com.parishjain.BlogginApi.service;

import com.parishjain.BlogginApi.models.BlogFollowing;
import com.parishjain.BlogginApi.models.BlogUser;
import com.parishjain.BlogginApi.repository.IFollowingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingService {

    @Autowired
    IFollowingRepo followingRepo;

    public void saveFollowing(BlogUser blogUser, BlogUser otherUser) {
        BlogFollowing blogFollowing = new BlogFollowing(null,blogUser,otherUser);
        followingRepo.save(blogFollowing);
    }
}
