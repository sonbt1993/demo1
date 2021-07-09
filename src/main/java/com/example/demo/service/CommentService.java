package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;

import java.util.List;

public interface CommentService {
    List<Comment> getALlCommentOfPost(Post post);
    Comment getCommentById(Long id);
    Comment save(Comment comment);
    void removeComment(Comment comment);
}
