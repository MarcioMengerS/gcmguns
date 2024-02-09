package br.com.gcm.sac.setor_armamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.mail.Email;
import br.com.gcm.sac.setor_armamento.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("email")
public class EmailController {
    @Autowired
    private final EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService =emailService;
    }

    @PostMapping
    public void sendEmail(@RequestBody Email email) {
        emailService.sendEMail(email);
    }
    
}
