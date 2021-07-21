package com.example.demo.service.impl;

import com.example.demo.repository.TagRepository;
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
        return tagRepository.getAllTag();
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag findById(int id) {
        return tagRepository.findById(id);
    }

    @Override
    public void deleteById(int tagId) {
        tagRepository.deleteById(tagId);
    }
}
