package com.example.demo.controller;


import com.example.demo.DTO.*;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.service.IPostService;
import com.example.demo.service.ITagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    IPostService postService;
    @Autowired
    ITagService tagService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String home(Model model) {
        List<Tag> tags = tagService.getAllTag();
        List<Post> posts = postService.getAllPost();
        List<User> userList = userService.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);
        model.addAttribute("userList", userList);
        return "index";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User personal = userService.findUserByUsername(principal.getName());
        model.addAttribute("personal", personal);
        return "user";
    }

    @GetMapping("/user/{name}")
    public String userPage(@PathVariable("name") String name, Model model) {
        User personal = userService.findUserByUsername(name);
        model.addAttribute("personal", personal);
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

//    @GetMapping("/error")
//    public String errorPage() {
//        return "error";
//    }



}
