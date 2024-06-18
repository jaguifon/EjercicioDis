package com.example.JAF;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ObjetoService {
    JSONManager jsonManager = new JSONManager();
    // This method should be implemented to return a list of Objeto instances
    public static ArrayList<Objeto> getAllObjetos() {

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


    // Función para actualizar un barco
    public static void updateObjeto(Objeto existingShip) {
        new JSONManager().updateObjeto(existingShip);
    }

    // Función para obtener un barco por su nombre
    public static Objeto getObjetoByName(String Name) {
        return new JSONManager().getObjetoInfo(Name);
    }
}