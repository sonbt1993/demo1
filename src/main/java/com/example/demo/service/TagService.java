package com.example.demo.service;


import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    List<Tag> getAllTag();
    List<Tag> getAllTagOfPost(Post post);
    List<Tag> save(Tag tag);
}
