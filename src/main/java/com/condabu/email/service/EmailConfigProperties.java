package com.condabu.email.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(
        prefix = "spring.mail"
)
public class EmailConfigProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean smtpAuth;
    private boolean starttlsEnable;
    private JavaMailSender javaMailSender;

    public EmailConfigProperties() {
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isSmtpAuth() {
        return this.smtpAuth;
    }

    public boolean isStarttlsEnable() {
        return this.starttlsEnable;
    }

    public JavaMailSender getJavaMailSender() {
        return this.javaMailSender;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setSmtpAuth(final boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public void setStarttlsEnable(final boolean starttlsEnable) {
        this.starttlsEnable = starttlsEnable;
    }
}
