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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

//        User accessingUser = userService.findUserByUsername(principal.getName());
//        UserDTO accessingUserDTO = userMapper.userToUserDTO(accessingUser);
//
//        model.addAttribute("accessingUserDTO", accessingUserDTO);

        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);

        return "postList";
    }

    @GetMapping("/admin/users")
    public String userList(Model model){
        List<User> userList = userService.findAll();
        List<UserDTO> userDTOList = userMapper.usersToUserDTOs(userList);

        model.addAttribute("userDTOList", userDTOList);

        return "userList";
    }

}
