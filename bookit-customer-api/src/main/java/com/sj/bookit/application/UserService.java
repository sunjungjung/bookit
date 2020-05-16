package com.sj.bookit.application;

import com.sj.bookit.domain.User;
import com.sj.bookit.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService {

    UserRepository userRepostory;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepostory,
                       PasswordEncoder passwordEncoder) {
        this.userRepostory = userRepostory;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String name, String password) {

        Optional<User> existed = userRepostory.findByEmail(email);
        if (existed.isPresent()) {
            throw new EmailExistException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .level(1L)
                .build();

        return userRepostory.save(user);
    }

}
