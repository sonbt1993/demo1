package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    @NaturalId
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    //Một User viết nhiều Post
    @OneToMany(
            mappedBy="author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Post> posts;

    public void addPost(Post post) {
        posts.add(post);
        post.setAuthor(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setAuthor(null);
    }


    //Một User viết nhiều Comment
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_comment_id")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setCommenter(this);
    }
    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setCommenter(null);
    }



}
