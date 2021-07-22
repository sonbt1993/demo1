package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User findUserByUsername(String username);
    User findUserById(Long id);
    Page<UserDTO> getUserDTOs(int pageNum, String sortField, String sortDir);
    void deleteById(Long userId);
    User save(User user);
    void transferMoney(String sender, String receiver, Double amount);
    void deduct(String sender, double amount);
    void deposit(String receiver, double amount);


}
