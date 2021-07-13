package com.example.demo.service;


import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    List<Tag> getAllTag();
    List<Tag> getAllTagOfPost(Post post);
    Tag save(Tag tag);
    Page<Tag> listAll(int pageNum, String sortField, String sortDir);
    Tag findById(int id);
    void deleteById(int tagId);
}
