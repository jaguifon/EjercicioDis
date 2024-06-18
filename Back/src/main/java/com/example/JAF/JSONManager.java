package com.example.JAF;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.JAF.ObjetoService.getAllObjetos;

public class JSONManager {
    public static ArrayList<Objeto> getListaObjetos() {
        ArrayList<Objeto> ships = new ArrayList<Objeto>();
        Type userListType = new TypeToken<ArrayList<Objeto>>() {
        }.getType();

        try {
            FileReader reader = new FileReader("data.json");
            ships = new Gson().fromJson(reader, userListType);
            System.out.println(ships);
            reader.close();
            return ships;

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateObjeto (Objeto updatedObjeto) {
        ArrayList<Objeto> objetos = getListaObjetos();

        // Buscar el objeto por su nombre y actualizarlo
        for (Objeto objeto : objetos) {
            if (objeto.getName().equals(updatedObjeto.getName())) {
                // Actualizar los atributos del objeto
                objeto.setModel(updatedObjeto.getModel());
                objeto.setCostInCredits(updatedObjeto.getCostInCredits());
                objeto.setCrew(updatedObjeto.getCrew());
                objeto.setCargoCapacity(updatedObjeto.getCargoCapacity());
                objeto.setConsumables(updatedObjeto.getConsumables());
                objeto.setHyperdriveRating(updatedObjeto.getHyperdriveRating());
                objeto.setStarshipClass(updatedObjeto.getStarshipClass());
                objeto.setPilots(updatedObjeto.getPilots());
                objeto.setFilms(updatedObjeto.getFilms());

                // Guardar los cambios en el archivo JSON
                saveObjetosList(objetos);
                return; // Terminar la ejecución después de actualizar el objeto
            }
        }
    }

    public Objeto getObjetoInfo(String name) {
        // Get the list of all Objeto instances
        ArrayList<Objeto> objetos = getListaObjetos();

        // Iterate over the list and find the Objeto instance with the given name
        for (Objeto objeto : objetos) {
            if (objeto.getName().equals(name)) {
                return objeto;
            }
        }

        // If no Objeto instance with the given name is found, return null
        return null;
    }
    private void saveObjetosList(ArrayList<Objeto> ships) {
        try {
            FileWriter writer = new FileWriter("data.json");
            new Gson().toJson(ships, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
