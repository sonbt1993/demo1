package com.example.demo.service;

import com.example.demo.repository.CommentRepository;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getALlCommentOfPost(Post post) {
        return commentRepository.findCommentsByPost(post);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findCommentById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
