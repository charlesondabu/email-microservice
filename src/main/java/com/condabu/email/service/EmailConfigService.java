package com.condabu.email.service;


import com.condabu.email.model.EmailConfig;
import com.condabu.email.repository.EmailConfigRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class EmailConfigService {
    @Autowired
    private EmailConfigRepository configRepository;
    public EmailConfig getConfigById(Long id) {
        return (EmailConfig)this.configRepository.findById(id).orElse((EmailConfig) null);
    }
}
