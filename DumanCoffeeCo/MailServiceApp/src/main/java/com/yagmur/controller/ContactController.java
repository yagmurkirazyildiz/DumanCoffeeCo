package com.yagmur.controller;

import com.yagmur.dto.ContactRequest;
import com.yagmur.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private MailSenderService mailService;

    @PostMapping("/send")
    @CrossOrigin("*")
    public String sendContactMessage(@RequestBody ContactRequest contactRequest) {
        mailService.sendContactEmail(contactRequest.getName(), contactRequest.getEmail(), contactRequest.getMessage());
        return "Mesaj başarıyla gönderildi!";
    }
}

