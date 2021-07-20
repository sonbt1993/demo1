package com.example.demo.utils;

import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class TagValidator implements Validator {
    @Autowired
    TagRepository tagRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass== Tag.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
            Tag tagForm = (Tag) target;

            String name = tagForm.getName();
            if(name!=null&&name.length() > 0){

                Tag tag = tagRepository.findTagByName(name);
                if (tag != null) {
                    errors.rejectValue("name", "Duplicate.tag.name");
                }
            }
    }
}
