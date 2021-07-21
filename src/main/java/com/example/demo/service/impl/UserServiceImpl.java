package com.example.demo.service.impl;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;



    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }



    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }


    @Override
    public Page<UserDTO> getUserDTOs(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDTO> dtos = userMapper.usersToUserDTOs(userPage.getContent());
        return new PageImpl<>(dtos, pageable, userPage.getTotalElements());
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


}
