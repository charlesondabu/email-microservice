package com.condabu.email.repository;

import com.condabu.email.model.Group;
import jakarta.mail.MessagingException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    public interface MailService {
        void sendEmail(String to, String bcb, String subject, String body) throws MessagingException;

        void sendMailAttachment(String to, String from, String subject, String body, String attachment) throws MessagingException;

        void sendEmail(String[] to, String[] bcc, String subject, String body) throws MessagingException;
    }
}
