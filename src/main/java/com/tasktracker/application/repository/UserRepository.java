package com.tasktracker.application.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tasktracker.application.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long Id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
