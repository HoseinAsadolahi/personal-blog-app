package com.github.hoseinasadolahi.bloggingapp.repository;

import com.github.hoseinasadolahi.bloggingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
