package com.finestweber.msemail.controller;

import com.finestweber.msemail.dto.EmailDto;
import com.finestweber.msemail.model.EmailModel;
import com.finestweber.msemail.service.EmailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/emails") // Prefixo para todas as rotas do controlador
public class EmailController {

    @Autowired
    EmailService emailService;

    // Endpoint para enviar um e-mail
    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendingEmail(@Valid @RequestBody EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    // Endpoint para obter todos os e-mails
    @GetMapping("/all")
    public ResponseEntity<List<EmailModel>> getAllEmails() {
        List<EmailModel> allEmails = emailService.getAllEmails();
        if (!allEmails.isEmpty()) {
            return ResponseEntity.ok(allEmails); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}

/*
* import com.finestweber.msemail.dto.EmailDto;
import com.finestweber.msemail.model.EmailModel;
import com.finestweber.msemail.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestParam("from") String from,
                                                   @RequestParam("to") String to,
                                                   @RequestParam("subject") String subject,
                                                   @RequestParam("text") String text){
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailFrom(from);
        emailModel.setEmailTo(to);
        emailModel.setSubject(subject);
        emailModel.setText(text);

        emailService.sendEmail(emailModel);

        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}

* */
