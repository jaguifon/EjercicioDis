package org.vaadin.example;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;

@Service
public class GreetService implements Serializable {

    public  ArrayList<Objeto> getObjetos() {
        return new ObjetoRequests().getObjetos();
    }

    public ArrayList<Objeto> senPDFRequest(Objeto objeto) {
        return new ObjetoRequests().sendPDFRequest(objeto);
    }

    public Objeto editObjetos(Objeto datosEditados) {
        System.out.println("Editing ShipRequest for " + datosEditados);
        return new ObjetoRequests().editRequest(datosEditados);
    }
}
