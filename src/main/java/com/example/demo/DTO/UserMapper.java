package com.example.demo.DTO;

import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userDTOToUser(UserDTO userDTO);
    UserDTO userToUserDTO(User user);
}