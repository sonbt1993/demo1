package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findUserByUsername(String username);
    User findUserById(Long id);
    Page<User> listAll(int pageNum, String sortField, String sortDir);
    Page<UserDTO> getUserDTOs(int pageNum, String sortField, String sortDir);
    void deleteById(Long userId);


}
