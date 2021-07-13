package com.example.demo.DTO;

import com.example.demo.entity.Tag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime lastUpdate;
    private UserDTO author;
    private List<TagDTO> tags;
}
