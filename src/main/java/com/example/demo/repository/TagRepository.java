package com.example.demo.repository;

import com.example.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT t FROM tag AS t WHERE t.id >= 1 order by t.id ASC")
    List<Tag> getAllTag();
    Tag save(Tag tag);
    Tag findById(int Id);


//    List<Tag> getAllByPost(Post post);
}
