package com.example.JAF;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ObjetoService {

    // This method should be implemented to return a list of Objeto instances
    public ArrayList<Objeto> getAllObjetos() {

        return new JSONManager().getListaObjetos();
    }
    public static Objeto getPDF(Objeto objeto) {
        if (objeto == null || objeto.getName() == null || objeto.getName().isEmpty()) {
            return null;
        }
        PDFManager pdfManager = new PDFManager();
        pdfManager.createPDF(objeto);
        return objeto;
    }
}