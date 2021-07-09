package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserMapper;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostRepository postRepository;
    @Autowired
    TagRepository tagRepository;

    @GetMapping("/admin/posts")
    public String postList(Model model, Principal principal){
        List<Tag> tags = tagService.getAllTag();
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);
        return "postList";
    }

    @GetMapping("/admin/users/{pageNum}")
    public String adminPage(Model model, @PathVariable(name = "pageNum") int pageNum,
                           @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){

        List<User> userList = userService.findAll();

        Page<UserDTO> userDTOPage = userService.getUserDTOs(pageNum,sortField,sortDir);
        model.addAttribute("userDTOPage", userDTOPage);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", userDTOPage.getTotalPages());
        model.addAttribute("totalItems", userDTOPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "adminPage";
    }

    @GetMapping("/admin/delete/{userId}")
    public String deleteUser( Model model, @PathVariable("userId") Long userId) {
        userService.deleteById(userId);
        return "redirect:/admin/users";
    }

}
