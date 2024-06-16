package com.example.JAF;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import com.example.JAF.Objeto;




public class PDFManager {
    public void createPDF(Objeto objeto) {

        // This method should be implemented to create a PDF file with the information of the given Objeto instance
        try {
            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("naves/" + objeto.getName() + ".pdf"));
            doc.open();
            String text = "some padding text";
            Paragraph p1 = new Paragraph(objeto.getName());
            p1.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p1);
            p1 = new Paragraph(objeto.getModel());
            doc.add(p1);
            p1 = new Paragraph(objeto.getStarshipClass());
            doc.add(p1);
            p1 = new Paragraph(objeto.getCrew());
            doc.add(p1);
            p1 = new Paragraph("Number of movies: " + objeto.getFilms().length);
            doc.add(p1);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

