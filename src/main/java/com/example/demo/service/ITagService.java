package com.example.demo.service;


import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> getAllTag();
    List<Tag> getAllTagOfPost(Post post);
    List<Tag> save(Tag tag);
}
