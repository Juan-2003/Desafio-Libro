package com.example.desafioLiteratura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapearDatos {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T mapearDatos(String json, Class<T>clase){
        try{
            return objectMapper.readValue(json, clase);
        }catch(JsonProcessingException e){
            System.out.println("Error al mapear los datos: " + e);
            throw new RuntimeException(e);
        }
    }
}
