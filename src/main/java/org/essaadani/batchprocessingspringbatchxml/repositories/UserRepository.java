package org.essaadani.batchprocessingspringbatchxml.repositories;


import org.essaadani.batchprocessingspringbatchxml.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
