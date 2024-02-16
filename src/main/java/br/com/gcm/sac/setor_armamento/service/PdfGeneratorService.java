package br.com.gcm.sac.setor_armamento.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.gcm.sac.setor_armamento.model.Equipment;
import br.com.gcm.sac.setor_armamento.model.Gcm;

@Service
public class PdfGeneratorService {

    private String paragraphDefaultOne = "Guarda Municipal declara ter RECEBIDO do supervisor da Equipe de\r\n" + //
        "armamento e comunicação - EARC, o material abaixo discriminado:";
    
    private String paragraphDefaultTwo = "Declaro ter conhecimento e estar de acordo com as RESPONSABILIDADES DECORRENTES DA POSSE DO\r\n" + //
        "DO EQUIPAMENTO descrito\r\n" + //
        "Em conhecendo e subordinando-se às condições impostas pelo presente instrumento cabe ao detentor zelar\r\n" + //
        "pela conservação do equipamento, sob sua posse;";

    public byte[] createPdf(Gcm gcm,Equipment equipment) throws IOException, DocumentException{
        ZonedDateTime dataHoraZonaAgora = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, baos);
        document.open();
        //Insere imagem no documento
        Image image = Image.getInstance("C:\\Users\\Marcio\\Documents\\Projects\\sac_gcm\\setor_armamento\\src\\main\\java\\br\\com\\gcm\\sac\\setor_armamento\\images\\brasaoPMPA.png");
        image.scaleAbsolute(150, 50);
        image.setAlignment(Image.ALIGN_RIGHT);
        document.add(image);

        Paragraph paragraph1 = new Paragraph(paragraphDefaultOne);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);

        Paragraph paragraph2 = new Paragraph(paragraphDefaultTwo);
        paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph2);

        Paragraph paragraph3 = new Paragraph(dataHoraZonaAgora.toString());
        paragraph3.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph3);
        
        Paragraph paragraph4 = new Paragraph(gcm.getNome()+equipment.getBrand());
        paragraph4.setAlignment(Paragraph.HEADER);
        document.add(paragraph4);

        document.close();
        return baos.toByteArray();
    }
}
