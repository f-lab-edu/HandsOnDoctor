package org.chat.handsondoctor.service;


import org.chat.handsondoctor.model.User;
import org.chat.handsondoctor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }
}
