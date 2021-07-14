package com.example.demo.service;

import com.example.demo.DTO.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<Post> getAllPost();
    List<Post> getAllPostOfUser(User user);

    void addPostIntoUser(Post post);
    Post findPostById(Long id);
    void deletePostById(Long id);
    Long findUserIdByPostId(Long postId);
    List<Post> findPostByTagId (int tagId);
    Page<Post> listAll(int pageNum, String sortField, String sortDir);
    Page<PostDTO> listAllDTO(int pageNum, String sortField, String sortDir);
    Page<Post> findAll(Pageable pageable);




}
