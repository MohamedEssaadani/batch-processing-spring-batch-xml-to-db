package org.essaadani.batchprocessingspringbatchxml.mappers;

import org.essaadani.batchprocessingspringbatchxml.dto.UserDTO;
import org.essaadani.batchprocessingspringbatchxml.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserDTOToUser(UserDTO userDTO);
}
