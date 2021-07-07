package com.example.demo.service;

import com.example.demo.repository.PostRepository;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostOfUser(User user) {
        return postRepository.findByAuthor(user);
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    @Override
    public void addPostIntoUser(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post editPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
