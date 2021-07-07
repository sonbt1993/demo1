package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;

import java.util.List;

public interface IPostService {
    List<Post> getAllPost();
    List<Post> getAllPostOfUser(User user);

    Post getPostById(Long id);
    void addPostIntoUser(Post post);
    Post editPostById(Long id);
    void deletePostById(Long id);


}
