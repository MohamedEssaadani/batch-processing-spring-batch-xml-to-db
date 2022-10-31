package org.essaadani.batchprocessingspringbatchxml.processor;

import org.essaadani.batchprocessingspringbatchxml.dto.UserDTO;
import org.essaadani.batchprocessingspringbatchxml.entities.User;
import org.essaadani.batchprocessingspringbatchxml.mappers.UserMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserItemProcessor implements ItemProcessor<UserDTO, User> {
    @Autowired
    UserMapper userMapper;

    @Override
    public User process(UserDTO userDTO) throws Exception {
        // map from user dto to user entity
        return userMapper.fromUserDTOToUser(userDTO);
    }
}
