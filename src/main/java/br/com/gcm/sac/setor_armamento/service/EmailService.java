package br.com.gcm.sac.setor_armamento.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.gcm.sac.setor_armamento.mail.Email;

@Service
@Component
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${email.remetente}")
    private String emailRemetente;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEMail(Email email){
        var message = new SimpleMailMessage();
        message.setFrom(emailRemetente);
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());
        mailSender.send(message);
    }
}
