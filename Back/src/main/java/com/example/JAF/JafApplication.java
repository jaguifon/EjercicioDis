package com.example.JAF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JafApplication {

	public static void main(String[] args) {

		SpringApplication.run(JafApplication.class, args);

		JSONManager.getListaObjetos();
		
		PDFManager pdfManager = new PDFManager();
		Objeto objeto = new Objeto("name", "model", "costInCredits", "crew", "cargoCapacity", "consumables", "hyperdriveRating", "starshipClass", new String[]{"pilots"}, new String[]{"films"});
		pdfManager.createPDF(objeto);
		

	}



}
