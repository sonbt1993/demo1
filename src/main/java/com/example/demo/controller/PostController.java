package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserMapper;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ICommentService;
import com.example.demo.service.IPostService;
import com.example.demo.service.ITagService;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class PostController {
    @Autowired IPostService postService;
    @Autowired ITagService tagService;
    @Autowired IUserService userService;
    @Autowired ICommentService commentService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/post/{postId}")
    public String postDetail(Model model, @PathVariable("postId") Long postId ) {

        Post post =  postService.getPostById(postId);

        if (postId != null){
            model.addAttribute("comment", new Comment());
            model.addAttribute("post", post);
            model.addAttribute("tags", tagService.getAllTag());            ;
            model.addAttribute("postTags", post.getTags());

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
    public String savePost(Model model, @ModelAttribute(name = "post") Post post, Principal principal){
        User personal = userService.findUserByUsername(principal.getName());
        post.setAuthor(personal);
        postService.addPostIntoUser(post);
        return "redirect:/";
    }

    @GetMapping("/posts/edit")
    public String editPost(@PathVariable("postId") Long postId,
                           Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("post", postService.getPostById(postId));
        model.addAttribute("user", user);
        return "edit";
    }

//    @PostMapping("/posts/{postId}")
//    public String savePost(@ModelAttribute(name = "post") Post post, @ModelAttribute(name = "user") User user ) {
//        post.setComments(commentService.getALlCommentOfPost(post));
//        post.setAuthor(user);
//        postService.addPostIntoUser(post);
//        return "redirect:/posts/" + post.getId();
//    }

    @DeleteMapping("/post/delete/{postId}")
    public String deletePost( Model model, Principal principal, @PathVariable("postId") Long postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }
}
