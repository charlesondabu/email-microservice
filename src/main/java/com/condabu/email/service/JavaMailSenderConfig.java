package com.condabu.email.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfig implements InitializingBean {
    @Autowired
    private EmailConfigProperties emailConfigProperties;
    private JavaMailSender javaMailSender;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfigProperties.getHost());
        mailSender.setPort(this.emailConfigProperties.getPort());
        mailSender.setUsername(this.emailConfigProperties.getUsername());
        mailSender.setPassword(this.emailConfigProperties.getPassword());
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.emailConfigProperties.isSmtpAuth());
        properties.put("mail.smtp.starttls.enable", this.emailConfigProperties.isStarttlsEnable());
        mailSender.setJavaMailProperties(properties);
        this.javaMailSender = mailSender;
        return mailSender;
    }

    public void afterPropertiesSet() {
        this.emailConfigProperties.setJavaMailSender(this.javaMailSender);
    }
}
