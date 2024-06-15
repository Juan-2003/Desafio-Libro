package com.example.desafioLiteratura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idiomas idioma;

    private Integer descargas;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autores> autores;

    public Libro(){}
    public Libro(ApiResultados apiResultados){
        this.titulo = apiResultados.titulo();
        this.idioma = Idiomas.fromApi(apiResultados.idiomas().get(0));
        this.descargas = apiResultados.descargas();
        this.autores = new ArrayList<>();
        for(ApiAutores a : apiResultados.autores()){
            Autores autor = new Autores(a);
            autor.setLibro(this); // Establecer la relación bidireccional
            this.autores.add(autor);
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        String autoresNombres = autores.stream().map(Autores::getNombre).collect(Collectors.joining(", "));
        return "Titulo: " + titulo + "\n" +
                "Autores: " +  autoresNombres + "\n" +
                "Idioma: " + idioma + "\n" +
                "Numero Descargas:" + descargas + "\n";
    }
}
