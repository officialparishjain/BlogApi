package com.parishjain.BlogginApi.repository;

import com.parishjain.BlogginApi.models.BlogFollowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowingRepo extends JpaRepository<BlogFollowing,Long> {
}
