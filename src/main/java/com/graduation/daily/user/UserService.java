package com.graduation.daily.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User_register create(String userid,String email, String password) {
        User_register user = new User_register();

        user.setUserid(userid);
        user.setEmail(email);
        user.setPassword(password);

        this.userRepository.save(user);
        return user;
    }
}
