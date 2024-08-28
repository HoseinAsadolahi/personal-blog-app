package com.github.hoseinasadolahi.bloggingapp.services;

import com.github.hoseinasadolahi.bloggingapp.exceptions.UserAlreadyExistsException;
import com.github.hoseinasadolahi.bloggingapp.model.User;
import com.github.hoseinasadolahi.bloggingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void registerNewUser(User user) throws UserAlreadyExistsException {
        if (userExists(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(user);
    }

    private boolean userExists(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.isPresent();
    }
}
