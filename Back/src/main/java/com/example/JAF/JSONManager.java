package com.example.JAF;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

}
