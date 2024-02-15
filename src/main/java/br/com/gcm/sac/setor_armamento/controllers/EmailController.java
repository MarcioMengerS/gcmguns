package br.com.gcm.sac.setor_armamento.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.model.Email;
import br.com.gcm.sac.setor_armamento.service.EmailService;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService =emailService;
    }

    @PostMapping("/sendMail")
    public String sendEmail(@RequestBody Email email){
        return emailService.sendEmail(email);
    }
 
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@ModelAttribute Email email) throws MessagingException{
        return emailService.sendMailWithAttachment(email);
    }
}
