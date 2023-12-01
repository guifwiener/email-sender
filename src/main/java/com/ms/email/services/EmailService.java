package com.ms.email.services;

import com.ms.email.dtos.EmailDto;
import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import com.ms.email.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    public List<EmailModel> sendEmail(EmailDto emailDto) {
        List<String> subscriberEmailList = getUserEmailList();
        List<EmailModel> emailSends = new ArrayList<>();


        for (int i=0; i<subscriberEmailList.size(); i++) {

            EmailModel emailModel = new EmailModel();

            try{
                BeanUtils.copyProperties(emailDto, emailModel);
                SimpleMailMessage message = new SimpleMailMessage();
                emailModel.setSendDateEmail(LocalDateTime.now());
                message.setFrom(emailModel.getEmailFrom());
                message.setSubject(emailModel.getSubject());
                message.setText(emailModel.getText());
                emailModel.setEmailTo(getUserEmailList().get(i).toString());
                message.setTo(emailModel.getEmailTo());
                emailSender.send(message);
                emailModel.setStatusEmail(StatusEmail.SENT.name());
            } catch (MailException e) {
                emailModel.setStatusEmail(StatusEmail.ERROR.name());
            } finally {
                emailSends.add(emailModel);
            }
        }
        emailRepository.saveAll(emailSends);
        return emailSends;
    }

    private List<String> getUserEmailList(){

        return userRepository.getEmailList();
    }

}
