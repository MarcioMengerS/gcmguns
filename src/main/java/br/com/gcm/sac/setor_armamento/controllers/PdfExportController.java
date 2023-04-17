package br.com.gcm.sac.setor_armamento.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.gcm.sac.setor_armamento.service.PdfGeneratorService;

@Controller
public class PdfExportController {

    private final PdfGeneratorService pdfGeneratorService;

    public PdfExportController(PdfGeneratorService pdfGeneratorService){
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate")
    public void generateCautelaPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy/hh:mm:ss");
        String currentDateTime =  dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportCautela(response);
    }
}
