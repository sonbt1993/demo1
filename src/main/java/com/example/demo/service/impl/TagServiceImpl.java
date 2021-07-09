package com.example.demo.service.impl;

import com.example.demo.repository.TagRepository;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getAllTagOfPost(Post post) {
        return null;
    }

    @Override
    public List<Tag> save(Tag tag) {
        return null;
    }
}
