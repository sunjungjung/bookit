package com.sj.bookit.application;

import com.sj.bookit.domain.User;
import com.sj.bookit.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    UserRepository userRepostory;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepostory,
                       PasswordEncoder passwordEncoder) {
        this.userRepostory = userRepostory;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String email, String password) {
        User user = userRepostory.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistException(email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordIncorrectException();
        }

        return user;
    }

}
