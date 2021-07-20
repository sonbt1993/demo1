package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.service.TagService;
import com.example.demo.utils.TagValidator;
import com.example.demo.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TagValidator tagValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == Tag.class) {
            dataBinder.setValidator(tagValidator);
        }

    }

    @GetMapping("/admin/tags")
    public String tagList(Model model){
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);
        return "tagList";
    }

    @GetMapping("/admin/addTag")
    public String addTag( Model model) {
        Tag tag = new Tag();
        model.addAttribute("tag", tag);
        return "addTag";
    }

    @PostMapping("/admin/addTag")
    public String saveNewTag(Model model, @ModelAttribute(name = "tag")@Validated Tag tag, BindingResult result){
        if (result.hasErrors()) {
            return "addTag";
        }
        tagService.save(tag);
        return "redirect:/admin/tags";
    }

    @GetMapping("/admin/editTag")
    public String editTag(@RequestParam("tagId") int tagId, Model model ) {
        Tag tag = tagService.findById(tagId);
        model.addAttribute("tag", tag);
        return "editTag";
    }

    @PostMapping("/admin/editTag")
    public String saveEditTag(Model model, @ModelAttribute(name = "tag")@Validated Tag tag, BindingResult result){
        if (result.hasErrors()) {
            return "editTag";
        }
        tagService.save(tag);
        return "redirect:/admin/tags";
    }

    @GetMapping("/admin/deleteTag/{tagId}")
    public String deleteTag( Model model, @PathVariable("tagId") int tagId) {
        tagService.deleteById(tagId);
        return "redirect:/admin/tags/";
    }

}
