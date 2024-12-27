package com.yagmur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactEmail(String name, String email, String messageContent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("dumankahvesi@gmail.com");
        message.setSubject("Duman Kafe İletişim Mesajı");
        message.setText("Gönderen: " + name + "\nE-posta: " + email + "\nMesaj:\n" + messageContent);

        mailSender.send(message);
    }
}
