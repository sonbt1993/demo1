package com.example.demo.controller;


import com.example.demo.DTO.*;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;
//    @Autowired
//    PostRepository postRepository;
//    @Autowired
//    TagRepository tagRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/1?sortField=lastUpdate&sortDir=desc";
    }

    @GetMapping("/{pageNum}")
    public String home(Model model, HttpServletRequest request, @PathVariable(name = "pageNum") int pageNum,
                       @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {

        String message = messageSource.getMessage("hello", null, "default message", request.getLocale());
        String message2 = messageSource.getMessage("Welcome", null, "default message", request.getLocale());
        String message3 = messageSource.getMessage("to", null, "default message", request.getLocale());
        model.addAttribute("message2", message2);
        model.addAttribute("message3", message3);
        model.addAttribute("message", message);

        Page<Post> page = postService.listAll(pageNum, sortField, sortDir);

        Page<PostDTO> posts = postService.listAllDTO(pageNum, sortField, sortDir);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        List<Tag> tagList = tagService.getAllTag();
        List<TagDTO> tags = tagMapper.tagsToTagDTOS(tagList);
        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);
        return "index";
    }



    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

//    @GetMapping("/admin")
//    public String adminPage() {
//        return "admin";
//    }

//    @GetMapping("/error")
//    public String errorPage() {
//        return "error";
//    }



}
