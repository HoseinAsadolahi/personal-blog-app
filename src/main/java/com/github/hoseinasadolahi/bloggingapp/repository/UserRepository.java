package com.github.hoseinasadolahi.bloggingapp.repository;

import com.github.hoseinasadolahi.bloggingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
