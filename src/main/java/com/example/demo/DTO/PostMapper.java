package com.example.demo.DTO;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    PostDTO postToPostDTO(Post post);
    List<PostDTO> postsToPostDTOS(List<Post> posts);
}
