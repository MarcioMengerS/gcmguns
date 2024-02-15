package br.com.gcm.sac.setor_armamento.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.gcm.sac.setor_armamento.model.Email;

@Service
@Component
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendEmail(Email email){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("EARC teste de envio de email<"+sender+">");
            mailMessage.setTo(email.getRecipient());// recebedor do email
            mailMessage.setSubject(email.getSubject());//título do emal
            mailMessage.setText(email.getMessage());// mensagem enviada no corpo do email

            mailSender.send(mailMessage);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            return "Email enviado com erro: "+e;
        }
    }


    public String sendMailWithAttachment(Email email) throws MessagingException{
        try {
            MimeMessage mimiMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimiMessage, true);
            mimeMessageHelper.setFrom("EARC - GCM<"+sender+">");
            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getMessage());
            mimeMessageHelper.addAttachment(email.getAttachment().getOriginalFilename(), email.getAttachment());

            mailSender.send(mimiMessage);
            return "Mail sent successfully!";

        } catch (Exception e) {
            return "Mail sending error!"+e;
        }
    }
}
