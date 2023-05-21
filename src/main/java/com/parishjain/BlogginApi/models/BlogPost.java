package com.parishjain.BlogginApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Timestamp postDate;

    @Column(nullable = false)
    @NotNull(message = "Post Data Cannot be null..")
    private String postData;

    @NotBlank(message = "Post Caption cannot be blank...")
    @Column(nullable = false)
    private String postCaption;


    // HERE I HAVE USE MANY-TO-ONE RELATION SHIP BECAUSE
    // MANY POSTS CAN BE DONE BY ONE USER
    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private BlogUser blogUser;

}
