package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);
    User findUserById(Long id);
    Page<UserDTO> getUserDTOs(int pageNum, String sortField, String sortDir);
    void deleteById(Long userId);
    User save(User user);


}
