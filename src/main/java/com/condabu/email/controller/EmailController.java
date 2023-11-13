package com.condabu.email.controller;

import com.condabu.email.model.EmailMessage;
import com.condabu.email.model.EmailRequest;
import com.condabu.email.model.SimpleEmailRequest;
import com.condabu.email.service.EmailMessageService;
import com.condabu.email.service.SendMailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private SendMailService mailService;
    @Autowired
    private EmailMessageService emailMessageService;
    @RequestMapping(
            value = {"/api/mail/send-simple"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<?> sendSimpleMail(@RequestBody SimpleEmailRequest request) throws MessagingException {
        this.mailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        return ResponseEntity.ok("Email Sent");
    }

    @RequestMapping(
            value = {"/api/mail/send-attachment"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<?> sendEmailWithAttachment(@RequestBody EmailRequest request) throws MessagingException {
        this.mailService.sendMailAttachment(request.getTo(), request.getFrom(), request.getSubject(), request.getBody(), request.getAttachment());
        System.out.println("Sending email");
        return ResponseEntity.ok("Email Sent");
    }

    @RequestMapping(
            value = {"/api/mail/send-to-group"},
            method = {RequestMethod.POST}
    )
    public ResponseEntity<String> sendEmailToGroup(@RequestBody EmailMessage request) throws MessagingException {
        this.emailMessageService.sendEmailToGroup(request.getGroup(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok("Emails sent successfully!");
    }
}
