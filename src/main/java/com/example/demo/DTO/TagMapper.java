package com.example.demo.DTO;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    TagDTO tagToTagDTO(Tag tag);
    List<TagDTO> tagsToTagDTOS(List<Tag> tags);
    List<Tag> tagDTOStoTags(List<TagDTO> tagDTOS);
}
