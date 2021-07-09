package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CommentController {

    @Autowired private PostService postService;
    @Autowired private CommentService commentService;
    @Autowired private UserService userService;

    @PostMapping("/post/comment/{postId}")
    public String saveComment(@ModelAttribute(name = "comment") Comment comment,
                              @PathVariable("postId") Long postId,
                              Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        comment.setCommenter(user);
        comment.setPost(postService.getPostById(postId));
        commentService.save(comment);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/remove")
    public String removeComment(@ModelAttribute("commentId") Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        commentService.removeComment(comment);
        return "redirect:/post/" + comment.getPost().getId();
    }
}
