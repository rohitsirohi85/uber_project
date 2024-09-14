package com.uberApplication.uber.services.impl;

import com.uberApplication.uber.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

     private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail, String subject, String body) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setText(body);
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);

            javaMailSender.send(simpleMailMessage);
            log.info("email sent successfully");
        }
        catch (Exception e){
            log.error("cannot send mail:"+e.getMessage());
        }


    }

    @Override
    public void sendEmail(String[] toEmail, String subject, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


            simpleMailMessage.setBcc(toEmail);
            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject(subject);

            javaMailSender.send(simpleMailMessage);
            log.info("email sent successfully");
        }
        catch (Exception e){
            log.error("cannot send mail:"+e.getMessage());
        }
    }
}
