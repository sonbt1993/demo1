package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(Long id);

    List<Post> findByAuthor(User user);


    // Native SQL
    @Query(value = "SELECT author_id FROM post WHERE post.id = :postId" , nativeQuery = true)
    Long findUserIdByPostId( @Param("postId") Long postId );

    @Query(value = "select * from post inner join post_tag on post_tag.post_id=post.id where tag_id=?1",
            nativeQuery = true)
    List<Post> findPostByTagId (int tagId);

}
