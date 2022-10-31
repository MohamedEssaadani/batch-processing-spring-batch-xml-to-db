package org.essaadani.batchprocessingspringbatchxml.writer;

import org.essaadani.batchprocessingspringbatchxml.entities.User;
import org.essaadani.batchprocessingspringbatchxml.repositories.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class XmlToDatabaseUserWriter implements ItemWriter<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(List<? extends User> usersList) throws Exception {
        // write to database
        System.out.println("******************** LIST OF USERS RECEIVED *********************");
        System.out.println(usersList);
        usersList.forEach(item-> System.out.println(item.getName()));

        userRepository.saveAll(usersList);
    }
}
