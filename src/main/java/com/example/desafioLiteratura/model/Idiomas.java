package com.example.desafioLiteratura.model;

public enum Idiomas {
    ENGLISH("Ingles", "en"),
    SPANISH("Español", "es"),
    FRENCH("Frances", "fr"),
    DUTCH("Neerlandes", "nl"),
    RUSSIAN("Ruso", "ru"),
    JAPANESE("Japones", "ja"),
    GERMAN("German", "de"),
    PORTUGUESE("Portugues", "pt"),
    CHINESE("Chino", "zh");

    private String idiomaEspanol;
    private String idiomaApi;

    Idiomas(String idiomaEspanol, String idiomaApi){
        this.idiomaEspanol = idiomaEspanol;
        this.idiomaApi = idiomaApi;
    }

    public static Idiomas fromEspanol(String text){
        for(Idiomas idiomas : Idiomas.values()){
            if(idiomas.idiomaEspanol.equalsIgnoreCase(text)){
                return idiomas;
            }
        }
        throw new IllegalArgumentException("No se encontro un idioma: " + text);
    }

    public static Idiomas fromApi(String text){
        for(Idiomas idiomas : Idiomas.values()){
            if(idiomas.idiomaApi.equalsIgnoreCase(text)){
                return idiomas;
            }
        }
        throw new IllegalArgumentException("No se encontro un idioma: " + text);
    }
}
