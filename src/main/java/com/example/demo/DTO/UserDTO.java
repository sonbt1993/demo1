package com.example.demo.DTO;

import com.example.demo.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
