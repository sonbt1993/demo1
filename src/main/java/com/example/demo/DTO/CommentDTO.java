package com.example.demo.DTO;

import com.example.demo.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime lastUpdate;
    private UserDTO commenter;
}
