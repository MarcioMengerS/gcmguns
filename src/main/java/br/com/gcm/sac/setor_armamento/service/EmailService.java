package br.com.gcm.sac.setor_armamento.service;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendMailWithAttachment(byte[] serializedPdf){

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            mimeMessage.addHeader("Content-Type", "text/HTML; charset=UTF-8");//text/plain
            mimeMessage.setFrom("EARC - GCM<"+sender+">");
            mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("2018007159@restinga.ifrs.edu.br"));
            mimeMessage.setSubject("Equipamento GCM");

            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart texPart = new MimeBodyPart();
            texPart.setText("Mensagem Automática. Não responda esse EMAIL! "+
                "Caso NÃO reconheça esse email ligue para 32897004");
            multipart.addBodyPart(texPart);

            MimeBodyPart pdfPart = new MimeBodyPart();
            pdfPart.setDataHandler(new DataHandler(new ByteArrayDataSource(serializedPdf, "application/pdf")));
            pdfPart.setFileName("cautela.pdf");
            multipart.addBodyPart(pdfPart);

            mimeMessage.setContent(multipart);
            
            mailSender.send(mimeMessage);
            return "Email enviado com sucesso";
        } catch (MessagingException e) {
            return "Mail sending error!"+e;
        }
    }
}