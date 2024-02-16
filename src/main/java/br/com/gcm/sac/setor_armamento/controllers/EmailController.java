package br.com.gcm.sac.setor_armamento.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.itextpdf.text.DocumentException;

import br.com.gcm.sac.setor_armamento.model.Equipment;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.service.EmailService;
import br.com.gcm.sac.setor_armamento.service.EquipmentService;
import br.com.gcm.sac.setor_armamento.service.GcmService;
import br.com.gcm.sac.setor_armamento.service.PdfGeneratorService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class EmailController {
    @Autowired
    GcmService gcmService;

    @Autowired
    EquipmentService equipmentService;
    
    @Autowired
    EmailService emailService;

    @PostMapping("/sendMailWithAttachment/{idEquipment}/{numGcm}")//ModelAttribute usado com dados do tipo form-data @ModelAttribute String email
    public String sendMailWithAttachment(@PathVariable Integer idEquipment, @PathVariable Short numGcm) throws IOException, DocumentException{
        //Busca Equipamento no Bnaco de dados
        Equipment equipment = new Equipment();
        equipment = equipmentService.findById(idEquipment);

        //Busca Gcm no Banco de dados
        Gcm gcm = new Gcm();
        gcm = gcmService.findByNumber(numGcm);

        //Preenche documento PDF com as informações do equipamento e GCM
        PdfGeneratorService pdfGeneratorService = new PdfGeneratorService();
        byte[] serializedPdf = pdfGeneratorService.createPdf(gcm, equipment);

        //Envia email com anexo pdf serializado
        String responseEmail = emailService.sendMailWithAttachment(serializedPdf);
        return responseEmail;
    }
}
