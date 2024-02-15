package br.com.gcm.sac.setor_armamento.model;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String recipient; //email do recebedor
    private String subject; //título
    private String message; // texto no corpo da mensagem
    private MultipartFile attachment; //Anexo  
}
