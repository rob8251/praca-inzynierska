package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.entities.Role;
import com.rutrob.recipesapi.entities.User;
import com.rutrob.recipesapi.mappers.UserMapper;
import com.rutrob.recipesapi.repositories.RoleRepository;
import com.rutrob.recipesapi.repositories.UserRepository;
import com.rutrob.recipesapi.rest.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;
    private final UserMapper userMapper;


    public void createUser(SignUpRequest signUpRequest) {
        userRepository.findByUsername(signUpRequest.username())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Username already exists");
                });
        // TODO: Check for email uniqueness

        User user = userMapper.signUpRequestToUser(signUpRequest);

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("No such role found"));

        user.setRoles(Set.of(role));
        user.setPassword(encoder.encode(CharBuffer.wrap(signUpRequest.password())));

        userRepository.save(user);
    }

}
