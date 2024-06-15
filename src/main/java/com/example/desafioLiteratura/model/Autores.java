package com.example.desafioLiteratura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fechaNacimiento;
    private String fechaDefuncion;

    @ManyToOne
    private Libro libro;

    public Autores(){}
    public Autores(ApiAutores apiAutores){
        this.nombre = apiAutores.nombre();
        this.fechaNacimiento = apiAutores.fechaNacimiento();
        this.fechaDefuncion = apiAutores.fechaDefuncion();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
