package com.ms.email.services;

import com.ms.email.models.UserModel;
import com.ms.email.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel createUser(UserModel userModel){
        return userRepository.save(userModel);
    }
}
