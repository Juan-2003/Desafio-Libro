package com.example.desafioLiteratura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarAPI {
    public static String consultarApi(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(IOException | InterruptedException e){
            System.out.println("Error al consultar la API: " + e);
            throw new RuntimeException(e);
        }
        return response.body();
    }
}
