package com.example.demo.service.impl;

import com.example.demo.DTO.PostDTO;
import com.example.demo.DTO.PostMapper;
import com.example.demo.DTO.UserDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;
@Transactional(rollbackFor = Exception.class)
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostMapper postMapper;

    @PostConstruct
    public void postConstruct(){
        System.out.println("\t>> Đối tượng PostService sau khi khởi tạo xong sẽ chạy hàm này");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("\t>> Đối tượng PostService trước khi bị destroy thì chạy hàm này");
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostOfUser(User user) {
        return postRepository.findByAuthor(user);
    }

    @Override
    public void addPostIntoUser(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Long findUserIdByPostId(Long postId) {
        return postRepository.findUserIdByPostId(postId);
    }

    @Override
    public List<Post> findPostByTagId(int tagId) {
        return postRepository.findPostByTagId(tagId);
    }

    @Override
    public Page<Post> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<PostDTO> listAllDTO(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDTO> dtos = postMapper.postsToPostDTOS(postPage.getContent());
        return new PageImpl<>(dtos, pageable, postPage.getTotalElements());
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return  postRepository.findAll(pageable);
    }


}
