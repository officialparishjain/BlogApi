package com.parishjain.BlogginApi.repository;

import com.parishjain.BlogginApi.models.BlogFollower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowerRepo extends JpaRepository<BlogFollower,Long> {
}
