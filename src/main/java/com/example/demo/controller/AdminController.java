package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserMapper;
import com.example.demo.entity.Post;
import com.example.demo.entity.Role;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.RoleService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserMapper userMapper;


    @GetMapping("/admin/adminPage")
    public String userList(){
        return "adminPage";
    }

    @GetMapping("/admin/posts/{pageNum}")
    public String postList(Model model, Principal principal, @PathVariable(name = "pageNum") int pageNum,
                           @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){
        List<Tag> tags = tagService.getAllTag();
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);

        Page<Post> postPage = postService.listAll(pageNum, sortField, sortDir);
        model.addAttribute("postPage", postPage);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("totalItems", postPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "postList";
    }

    @GetMapping("/admin/users/{pageNum}")
    public String userList(Model model, @PathVariable(name = "pageNum") int pageNum,
                           @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){

        Page<UserDTO> userDTOPage = userService.getUserDTOs(pageNum,sortField,sortDir);
        model.addAttribute("userDTOPage", userDTOPage);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", userDTOPage.getTotalPages());
        model.addAttribute("totalItems", userDTOPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "userList";
    }

    @GetMapping("/admin/users/delete/{userId}")
    public String deleteUser( Model model, @PathVariable("userId") Long userId) {
        userService.deleteById(userId);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/createUser")
    public String createUser( Model model) {
        List<Role> roles = roleService.findAll();
        User user = new User();
        model.addAttribute("roles", roles);
//        model.addAttribute("userRole", userRole);
        model.addAttribute("user", user);
        return "addUser";
    }

    @GetMapping("/admin/users/editUser")
    public String editUser( Model model, @RequestParam("userId") Long userId ) {

        List<Role> roles = roleService.findAll();
        User user = userService.findUserById(userId);
        if(user==null){
            return "error";
        };
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/admin/users/saveUser")
    public String saveNewUser(Model model, @ModelAttribute(name = "user") User user) {
        String newUserUsername = user.getUsername();
        User oldUser = userService.findUserByUsername(newUserUsername);
        if(oldUser!=null){
            return "error";
        };

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users/1?sortField=id&sortDir=asc";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        if(user==null){
            return "error";
        };
        UserDTO personal = userMapper.userToUserDTO(user);
        model.addAttribute("personal", personal);
        return "user";
    }

    @GetMapping("/user/{name}")
    public String userPage(@PathVariable("name") String name, Model model) {

        User personal = userService.findUserByUsername(name);
        if(personal==null){
            return "error";
        };
        model.addAttribute("personal", personal);
        return "user";
    }



}
