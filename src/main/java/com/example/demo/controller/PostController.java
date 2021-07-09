package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserMapper;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/post/{postId}")
    public String postDetail(Model model, @PathVariable("postId") Long postId, Principal principal ) {
        if(principal!=null){
            User accessingUser = userService.findUserByUsername(principal.getName());
            UserDTO accessingUserDTO = userMapper.userToUserDTO(accessingUser);
            model.addAttribute("accessingUserDTO", accessingUserDTO);
        };

        Post post =  postService.getPostById(postId);
        Long authorId = postService.findUserIdByPostId(postId);
        User author = userService.findUserById(authorId);
//        User author = postService.findUserByPostId(postId);
        UserDTO authorDTO = userMapper.userToUserDTO(author);
        if (postId != null){
            model.addAttribute("comment", new Comment());
            model.addAttribute("post", post);
            model.addAttribute("tags", tagService.getAllTag());            ;
            model.addAttribute("postTags", post.getTags());
            model.addAttribute("authorDTO", authorDTO);
        }
        return "post";
    }

    @GetMapping("/post/add")
    public String addPost( Model model, Principal principal) {
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("post", new Post());
        return "addPost";
    }

    @PostMapping("/post/add")
    public String saveNewPost(Model model, @ModelAttribute(name = "post") Post post, Principal principal){
        User personal = userService.findUserByUsername(principal.getName());
        post.setAuthor(personal);
        postService.addPostIntoUser(post);
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/post/edit")
    public String editPost(@RequestParam("postId") Long postId, Model model ) {
        Post post = postService.getPostById(postId);
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("post", postService.getPostById(postId));

        return "editPost";
    }

    @PostMapping("/post/edit")
    public String saveEditPost(Model model, @ModelAttribute(name = "post") Post post, Principal principal){
        User personal = userService.findUserByUsername(principal.getName());
        post.setAuthor(personal);
        postService.addPostIntoUser(post);
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/post/delete/{postId}")
    public String deletePost( Model model, @PathVariable("postId") Long postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }
}
