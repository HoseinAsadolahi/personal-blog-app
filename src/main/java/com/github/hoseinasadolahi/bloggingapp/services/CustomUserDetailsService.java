package com.github.hoseinasadolahi.bloggingapp.services;

import com.github.hoseinasadolahi.bloggingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.github.hoseinasadolahi.bloggingapp.model.User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("email not found!"));
        return new User(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
