package com.finestweber.msemail.service;

import com.finestweber.msemail.model.EmailModel;
import com.finestweber.msemail.model.StatusEmail;
import com.finestweber.msemail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {
    EmailRepository emailRepository;
    private JavaMailSender javaMailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }
    public EmailModel sendEmail(EmailModel emailModel){
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            javaMailSender.send(message);
            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return emailRepository.save(emailModel);
        }
    }

    public List<EmailModel> getAllEmails() {
        return emailRepository.findAll();
    }
}
