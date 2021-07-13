package com.example.demo.service.impl;

import com.example.demo.repository.TagRepository;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Tag> getAllTagOfPost(Post post) {
        return null;
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Page<Tag> listAll(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        return tagRepository.findAll(pageable);
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
