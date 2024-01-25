package com.ms.email.services;

import com.ms.email.dtos.UserCsvDto;
import com.ms.email.dtos.UserDto;
import com.ms.email.models.UserModel;
import com.ms.email.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel createUser(UserModel userModel){
        try {
            return userRepository.save(userModel);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<UserModel> createUsersFromCsv(UserCsvDto userCsvDto) throws IOException {
        List<UserModel> subscribersToCreate = new ArrayList<>();

        try {
            String path = userCsvDto.getPath();
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.readLine();

            while((line = reader.readLine()) != null){
                UserModel user = new UserModel();
                String[] values = line.split(",");
                user.setSubscriberName(values[0]);
                user.setSubscriberEmail(values[1]);
                subscribersToCreate.add(user);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            return userRepository.saveAll(subscribersToCreate);
        }
    }
}
