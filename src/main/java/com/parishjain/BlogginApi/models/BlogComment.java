package com.parishjain.BlogginApi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    private Timestamp commentDate;

    @NotNull(message = "User Comment body cannot be null... ")
    @Column(nullable = false)
    private String commentBody;

    @ManyToOne
    @JoinColumn(nullable = false , name = "fk_post_ID")
    private BlogPost post;

    @ManyToOne
    @JoinColumn(nullable = false , name = "fk_user_ID")
    private BlogUser user;


}
