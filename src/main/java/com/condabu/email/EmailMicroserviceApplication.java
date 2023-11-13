package com.condabu.email;

import com.condabu.email.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AutoConfiguration
@SpringBootApplication
public class EmailMicroserviceApplication {

	@Autowired
	private SendMailService sendMailService;
	public static void main(String[] args) {
		SpringApplication.run(EmailMicroserviceApplication.class, args);
	}

}
