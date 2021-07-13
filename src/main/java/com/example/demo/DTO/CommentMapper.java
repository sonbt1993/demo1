package com.example.demo.DTO;

import com.example.demo.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentDTO commentToCommentDTO(Comment comment);
    List<CommentDTO> commentsToCommentDTOS(List<Comment> comments);

}
