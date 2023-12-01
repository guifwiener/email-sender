package com.ms.email.controllers;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/email/send-all-subscribers")
    public ResponseEntity<List<EmailModel>> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        List<EmailModel> emailSends =  emailService.sendEmail(emailDto);
        return new ResponseEntity<List<EmailModel>>(emailSends, HttpStatus.CREATED);
    }
}
