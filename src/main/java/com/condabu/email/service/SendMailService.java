package com.condabu.email.service;

import com.condabu.email.model.EmailConfig;
import com.condabu.email.repository.ContactGroupRepository;
import com.condabu.email.repository.EmailConfigRepository;
import com.condabu.email.repository.EmailMessageRepository;
import com.condabu.email.repository.GroupRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

@Service
public class SendMailService implements GroupRepository.MailService {
    @Autowired
    private ContactGroupRepository contactGroupRepository;
    @Autowired
    private EmailMessageRepository emailMessageRepository;
    @Autowired
    private EmailConfigProperties emailConfigProperties;
    @Autowired
    private EmailConfigRepository configRepository;
    @Autowired
    private EmailConfigService configService;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            this.mailSender.send(mimeMessage);
            //LOGGER.info("Email sent to {}", to);
        } catch (MessagingException var6) {
            // LOGGER.error("Error sending email to {}: {}", new Object[]{to, var6.getMessage(), var6});
        }

    }

    public void sendEmail(String[] to, String[] bcc, String subject, String body) {
        this.setMailSenderProperties();

        try {
            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            if (bcc != null && bcc.length > 0) {
                helper.setCc(bcc);
            }

            helper.setSubject(subject);
            helper.setText(body, true);
            this.mailSender.send(message);
            //LOGGER.info("Email sent to {}", String.join(", ", to));
        } catch (MessagingException var7) {
            //LOGGER.error("Error sending email: {}", var7.getMessage(), var7);
        }

    }

    public void sendEmail(String to, String bcc, String subject, String body) {
    }

    public void sendMailAttachment(String to, String from, String subject, String body, String attachment) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(subject);
            FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
            mimeMessageHelper.addAttachment((String) Objects.requireNonNull(fileSystem.getFilename()), fileSystem);
            this.mailSender.send(mimeMessage);
            //LOGGER.info("Email with attachment sent to {}");
        } catch (MessagingException var9) {
            //LOGGER.error("Error sending email with attachment to {}: {}", new Object[]{to, var9.getMessage(), var9});
        }

    }

    private void setMailSenderProperties() {
        try {
            Optional<EmailConfig> optionalEmailConfig = this.configRepository.findById(2L);
            if (optionalEmailConfig.isPresent()) {
                EmailConfig emailConfig = optionalEmailConfig.get();
                // LOGGER.info("Mail Server is {}", emailConfig.getHost());
                if (emailConfig != null) {
                    JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) this.mailSender;
                    javaMailSender.setHost(emailConfig.getHost());
                    javaMailSender.setPort(emailConfig.getPort());
                    javaMailSender.setUsername(emailConfig.getUsername());
                    javaMailSender.setPassword(emailConfig.getPassword());
                    javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", String.valueOf(emailConfig.isAuth()));
                    javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", String.valueOf(emailConfig.isSecureConnection()));
                } else {
                    //LOGGER.error("EmailConfig with id 1 not found");
                }
            }
        }catch(Exception var3){
            //LOGGER.error("Error setting mail sender properties: {}", var3.getMessage(), var3);
        }
    }
}
