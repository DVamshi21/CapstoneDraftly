// src/main/java/com/gmailassistant/repository/UserRepository.java
package com.gmailassistant.gmail_ai_assistant.repository;

import com.gmailassistant.gmail_ai_assistant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}