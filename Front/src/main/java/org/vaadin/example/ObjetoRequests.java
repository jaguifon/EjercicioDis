package org.vaadin.example;

import com.googlecode.gentyref.TypeToken;
import com.nimbusds.jose.shaded.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ObjetoRequests {
    public ArrayList<Objeto> getObjetos() {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/objeto"))
                    .GET()
                    .build();

            HttpResponse<String> response = null;
            Gson gson = new Gson();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            Type shipsListType = new TypeToken<ArrayList<Objeto>>() {
            }.getType();
            return gson.fromJson(response.body(), shipsListType);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Objeto> sendPDFRequest(Objeto objeto) {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        ArrayList<Objeto> result = new ArrayList<>();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/objeto"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(objeto)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                result.add(objeto);
            }

        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public Objeto editRequest(Objeto datosEditados) {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        try {
            String shipJson = gson.toJson(datosEditados);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/" + datosEditados.getName().replaceAll(" ","%20")))  // Assuming Ship has an ID
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(shipJson))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Type shipType = new TypeToken<Objeto>() {}.getType();
                System.out.println(response.body());
                Objeto editedObjeto = gson.fromJson(response.body(), shipType);
                return editedObjeto;
            } else {
                throw new RuntimeException("Failed to edit ship: " + response.statusCode());
            }

        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

