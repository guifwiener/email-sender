package com.ms.email.controllers;

import com.ms.email.dtos.UserCsvDto;
import com.ms.email.dtos.UserDto;
import com.ms.email.models.UserModel;
import com.ms.email.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("subscriber/create")
    public ResponseEntity<UserModel> creatingSubscriber(@RequestBody @Valid UserDto userDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userService.createUser(userModel);
        return new ResponseEntity<>(userModel, HttpStatus.CREATED);
    }

    @PostMapping("subscriber/create-from-csv")
    public ResponseEntity<List<UserModel>> creatingSubscribersFromCsv(@RequestBody @Valid UserCsvDto userCsvDto) throws IOException {
        List<UserModel> subscribersCreated = userService.createUsersFromCsv(userCsvDto);
        return new ResponseEntity<List<UserModel>>(subscribersCreated, HttpStatus.CREATED);
    }
}
