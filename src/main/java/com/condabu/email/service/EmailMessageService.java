package com.condabu.email.service;
import com.condabu.email.model.ContactGroup;
import com.condabu.email.model.EmailMessage;
import com.condabu.email.repository.ContactGroupRepository;
import com.condabu.email.repository.EmailMessageRepository;
import com.condabu.email.repository.GroupRepository;
import jakarta.mail.MessagingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailMessageService {
    @Autowired
    private ContactGroupRepository contactGroupRepository;
    @Autowired
    private GroupRepository.MailService emailService;
    @Autowired
    private EmailMessageRepository emailMessageRepository;

    public EmailMessageService() {
    }

    public void sendEmailToGroup(String targetGroup, String subject, String messageBody) throws MessagingException {
        List<ContactGroup> contactsInGroup = this.contactGroupRepository.findByGroupGroupNameAndSendTo(targetGroup, true);
        List<ContactGroup> bccContacts = this.contactGroupRepository.findByGroupGroupNameAndSendTo(targetGroup, false);
        List<String> toEmails = contactsInGroup.stream().map((contactGroup) -> {
            return contactGroup.getContact().getEmail();
        }).toList();
        List<String> bccEmails = bccContacts.stream().map((contactGroup) -> {
            return contactGroup.getContact().getEmail();
        }).toList();
        String[] toArray = (String[])toEmails.toArray(new String[0]);
        String[] bccArray = (String[])bccEmails.toArray(new String[0]);
        System.out.println("To Email List: " + toEmails);
        System.out.println("BCC Email List: " + bccEmails);
        this.emailService.sendEmail(toArray, bccArray, subject, messageBody);
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setGroup(String.valueOf(targetGroup));
        emailMessage.setSubject(subject);
        emailMessage.setMessage(messageBody);
        emailMessage.setStatus("SENT");
        this.emailMessageRepository.save(emailMessage);
    }
}
