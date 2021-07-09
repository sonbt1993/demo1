package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<Post> getAllPost();
    List<Post> getAllPostOfUser(User user);

    Post getPostById(Long id);
    void addPostIntoUser(Post post);
    Post editPostById(Long id);
    void deletePostById(Long id);
    Long findUserIdByPostId(Long postId);
    List<Post> findPostByTagId (int tagId);
    User findUserByPostId(Long postId);
    Page<Post> listAll(int pageNum, String sortField, String sortDir);




}
