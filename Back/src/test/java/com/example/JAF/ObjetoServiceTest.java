
package com.example.JAF;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjetoServiceTest {

    private ObjetoService objetoService;

    @BeforeEach
    void setUp() {
        objetoService = new ObjetoService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllObjetos() {
        ArrayList<Objeto> objetos = objetoService.getAllObjetos();
        assertNotNull(objetos, "The list of objetos should not be null");
        assertFalse(objetos.isEmpty(), "The list of objetos should not be empty");
    }

    @Test
    void getPDF() {
        String[] films = {"https://findtheinvisiblecow.com/"};
        Objeto objeto = new Objeto(null, null, null, null, null, null, null, null, null,  films);
        objeto.setName("Test Name");
        Objeto returnedObjeto = objetoService.getPDF(objeto);
        assertNotNull(returnedObjeto, "The returned objeto should not be null");
        assertEquals(objeto.getName(), returnedObjeto.getName(), "The names of the input and returned objetos should be the same");
    }

    @Test
    void getAllObjetosReturnsExpectedNumberOfObjetos() {
        ArrayList<Objeto> objetos = objetoService.getAllObjetos();
        int expectedNumberOfObjetos = 34;  // Replace with the expected number of Objeto instances
        assertEquals(expectedNumberOfObjetos, objetos.size(), "The list of objetos should have the expected number of Objeto instances");
    }

    @Test
    void getAllObjetosReturnsObjetosWithNonNullAndNonEmptyNames() {
        ArrayList<Objeto> objetos = objetoService.getAllObjetos();
        for (Objeto objeto : objetos) {
            assertNotNull(objeto.getName(), "The name of the objeto should not be null");
            assertFalse(objeto.getName().isEmpty(), "The name of the objeto should not be empty");
        }
    }
}
