package com.github.hoseinasadolahi.bloggingapp.services;

import com.github.hoseinasadolahi.bloggingapp.model.User;
import com.github.hoseinasadolahi.bloggingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
