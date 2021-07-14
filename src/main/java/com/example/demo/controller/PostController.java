package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
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
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private PostMapper postMapper;

    @GetMapping("/post/{postId}")
    public String postDetail(Model model, @PathVariable("postId") Long postId, Principal principal ) {
        if(principal!=null){
            User accessingUser = userService.findUserByUsername(principal.getName());
            UserDTO accessingUserDTO = userMapper.userToUserDTO(accessingUser);
            model.addAttribute("accessingUserDTO", accessingUserDTO);
        };

        Post post =  postService.findPostById(postId);
        PostDTO postDTO = postMapper.postToPostDTO(post);
        List<Comment> comments = post.getComments();

        User author = post.getAuthor();

        UserDTO authorDTO = userMapper.userToUserDTO(author);
        if (postId != null){
            model.addAttribute("comment", new Comment());
            model.addAttribute("comments", comments);
            model.addAttribute("postDTO", postDTO);
            model.addAttribute("tags", tagMapper.tagsToTagDTOS( tagService.getAllTag()) );            ;
            model.addAttribute("postTags", postDTO.getTags());
            model.addAttribute("authorDTO", authorDTO);
        }
        return "post";
    }

    @GetMapping("/post/add")
    public String addPost( Model model) {
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);
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

        Post post = postService.findPostById(postId);
        User user = post.getAuthor();
        UserDTO author = userMapper.userToUserDTO(user);
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);
        model.addAttribute("author", author);
        model.addAttribute("post", post);
        return "addPost";
    }

    @PostMapping("/post/edit")
    public String saveEditPost(@ModelAttribute(name = "post") Post post,
                               @ModelAttribute(name = "author") UserDTO author){
        User user = userService.findUserById(author.getId());
        post.setAuthor(user);
        postService.addPostIntoUser(post);
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/post/delete/{postId}")
    public String deletePost( Model model, @PathVariable("postId") Long postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }
}
