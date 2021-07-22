package com.example.demo.controller;

import com.example.demo.DTO.PostDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserMapper;
import com.example.demo.entity.Role;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.model.TransferMoneyForm;
import com.example.demo.service.PostService;
import com.example.demo.service.RoleService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import com.example.demo.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserValidator userValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == User.class) {
            dataBinder.setValidator(userValidator);
        }


    }


    @GetMapping("/admin/adminPage")
    public String userList(){
        return "adminPage";
    }

    @GetMapping("/admin/posts/{pageNum}")
    public String postList(Model model, Principal principal, @PathVariable(name = "pageNum") int pageNum,
                           @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){
        List<Tag> tags = tagService.getAllTag();
//        List<Post> posts = postService.getAllPost();
//        Page<Post> postPage = postService.listAll(pageNum, sortField, sortDir);
        Page<PostDTO> postPage = postService.listAllDTO(pageNum, sortField, sortDir);
//        model.addAttribute("posts", postPage);
        model.addAttribute("tags", tags);
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
        return "redirect:/admin/users/1?sortField=id&sortDir=asc";
    }

    @GetMapping("/admin/users/createUser")
    public String createUser( Model model) {
        List<Role> roles = roleService.findAll();
        User user = new User();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/admin/users/saveUser")
    public String saveNewUser(@ModelAttribute(name = "user")@Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return "addUser";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users/1?sortField=id&sortDir=asc";
    }

    @GetMapping("/admin/users/edit")
    public String editUser( Model model, @RequestParam("userId") Long userId ) {

        List<Role> roles = roleService.findAll();
        User user = userService.findUserById(userId);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/admin/users/edit")
    public String saveEditUser(@ModelAttribute(name = "user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users/1?sortField=id&sortDir=asc";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        UserDTO personal = userMapper.userToUserDTO(user);
        model.addAttribute("personal", personal);
        return "user";
    }

//    @GetMapping("/user/{name}")
//    public String userPage(@PathVariable("name") String name, Model model) {
//
//        User personal = userService.findUserByUsername(name);
//        if(personal==null){
//            return "aerror";
//        };
//        model.addAttribute("personal", personal);
//        return "user";
//    }

    @GetMapping("/transferMoney")
    public String transferMoneyPage(Model model, Principal principal){
        User personal = userService.findUserByUsername(principal.getName());
        TransferMoneyForm transferMoneyForm = new TransferMoneyForm();
        transferMoneyForm.setSenderName(personal.getUsername());
        model.addAttribute("personal", personal);
        model.addAttribute("transferMoneyForm", transferMoneyForm);
        return "transferMoney";
    }

    @PostMapping("/transferMoney")
    public String transferMoney(@ModelAttribute(name = "personal") User personal,
                                @ModelAttribute(name = "transferMoneyForm") TransferMoneyForm transferMoneyForm){
        TransferMoneyForm form = transferMoneyForm;

        userService.transferMoney(form.getSenderName(),
                form.getReceiverName(),
                form.getAmount());
        return "redirect:/transferMoney";
    }



}
