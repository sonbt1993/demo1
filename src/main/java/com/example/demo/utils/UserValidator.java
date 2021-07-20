package com.example.demo.utils;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    @Autowired
    UserRepository userRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass== User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
            User userForm = (User) target;

            String username = userForm.getUsername();
            if(username!=null&&username.length() > 0){
                User user = userRepository.findUserByUsername(username);
                if (user != null) {
                    errors.rejectValue("username", "Duplicate.user.name");
                }
            }
    }
}
