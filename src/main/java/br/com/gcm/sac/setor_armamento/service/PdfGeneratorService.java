package br.com.gcm.sac.setor_armamento.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGeneratorService {
    
    @Autowired
    public GcmService gcmService;

    public void exportCautela(HttpServletResponse response) throws IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        // PdfWriter.getInstance(document, new FileOutputStream("Cautela.pdf"));
        document.open();

        //Cria logo da prefeitura de PoA
        Image logoPmpa = Image.getInstance("./src/main/java/br/com/gcm/sac/setor_armamento/images/brasaoPMPA.png");
        logoPmpa.scaleToFit(150, 50); //Define tamanho da imagem
        logoPmpa.setAlignment(Image.LEFT | Image.TEXTWRAP); //TEXTWRAP texto contorna a imagem

        //cria logo da prefeitura
        Font fontBrasao = FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL);
        Paragraph paragrafo = new Paragraph("PREFEITURA MUNICIPAL DE PORTO ALEGRE\n", fontBrasao);
        paragrafo.add("Secretaria Municipal de Segurança\n");
        paragrafo.add("Guarda Civil Metropolitana");
        paragrafo.setAlignment(Paragraph.ALIGN_LEFT);
        paragrafo.setSpacingAfter(25); //adiciona 25px após paragrafo

        //cria paragrafo de titulo com fonte helvetica tamanho 18 alinhado ao centro
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph paragraph = new Paragraph("CAUTELA", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);//adiciona 20px após título

        //cria paragrafo com fonte helvetica tamanho 12 alinhado a esquerda
        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        Paragraph paragraph2 = new Paragraph("O Servidor Público Guarda Municipal   ", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        String nomeGcm = gcmService.findById(1).getNome();
        paragraph2.add(nomeGcm.toUpperCase());

        document.addTitle("Cautela nº tal");
        document.addAuthor("GCM/SAC");
        document.addSubject("Cautela do equiamento tal para fulano de tal");
        document.add(logoPmpa);
        document.add(paragrafo);
        document.add(paragraph);
        document.add(paragraph2);
        document.close();
    }
}
