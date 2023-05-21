package com.parishjain.BlogginApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlogFollowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followingTableId;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private BlogUser user;

    @OneToOne
    @JoinColumn(name = "fk_following_user_id")
    private BlogUser following;

}
