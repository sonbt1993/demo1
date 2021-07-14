package com.example.demo.controller;


import com.example.demo.DTO.*;
import com.example.demo.entity.*;
import com.example.demo.repository.ChatRepository;
import com.example.demo.service.*;
import com.example.demo.shceduler.Chat;
//import com.example.demo.shceduler.ExpiredScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired private ChatRepository chatRepository;


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

    @GetMapping("/signup")
    public String signUpPage( Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/signup")
    public String saveSignUpPage(@ModelAttribute(name = "user") User user){
        String newUserUsername = user.getUsername();
        User account = userService.findUserByUsername(newUserUsername);
        int memberRoleId = 3;
        if(account!=null){
            return "error";
        }
        user.setRole(roleService.findRoleById(memberRoleId));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @GetMapping("/scheduler")
    public String scheduler(Model model) {
        List<Chat> chatList = chatRepository.findAll();
        model.addAttribute(chatList);
        return "scheduler";
    }

}
