package com.example.demo.controller;


import com.example.demo.DTO.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
//import com.example.demo.shceduler.ExpiredScheduler;
import com.example.demo.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserValidator userValidator;


    @PostConstruct
    public void postConstruct(){
        System.out.println("\t>> Đối tượng HomeController sau khi khởi tạo xong sẽ chạy hàm này");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("\t>> Đối tượng HomeController trước khi bị destroy thì chạy hàm này");
    }

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
    public String saveSignUpPage(@ModelAttribute(name = "user")@Validated User user, BindingResult result){
        if (result.hasErrors()) {
            return "signUp";
        }
        Integer memberRoleId = 3;
        user.setRole(roleService.findRoleById(memberRoleId));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


//    @GetMapping("/error")
//    public String errorPage() {
//        return "aerror";
//    }

//    @GetMapping("/scheduler")
//    public String scheduler(Model model) {
//        List<Chat> chatList = chatRepository.findAll();
//        model.addAttribute(chatList);
//        return "scheduler";
//    }

}
