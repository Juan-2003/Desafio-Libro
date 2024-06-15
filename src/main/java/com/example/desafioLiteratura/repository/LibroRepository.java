package com.example.desafioLiteratura.repository;

import com.example.desafioLiteratura.dto.AutorDTO;
import com.example.desafioLiteratura.dto.LibroDTO;
import com.example.desafioLiteratura.model.Autores;
import com.example.desafioLiteratura.model.Idiomas;
import com.example.desafioLiteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository  extends JpaRepository<Libro, Long> {

    //2.Listar libros registrados
    @Query("SELECT new com.example.desafioLiteratura.dto.LibroDTO(l.titulo, l.idioma, l.descargas, a.nombre) FROM Libro l INNER JOIN l.autores a")
    List<LibroDTO> obtenerLibrosRegistrados();

    //3.Listar autores registrados
    //@Query("SELECT a FROM Autores a")
    @Query("SELECT new com.example.desafioLiteratura.dto.AutorDTO(a.nombre, a.fechaNacimiento, a.fechaDefuncion, l.titulo) FROM Autores a INNER JOIN a.libro l")
    List<AutorDTO> obtenerAutoresRegistrados();

    //4.Listar autores vivos en un determinado año
    @Query("SELECT new com.example.desafioLiteratura.dto.AutorDTO(a.nombre, a.fechaNacimiento, a.fechaDefuncion, l.titulo) FROM Autores a INNER JOIN a.libro l WHERE :anio >= a.fechaNacimiento AND :anio < a.fechaDefuncion")
    //@Query("SELECT a FROM Autores a WHERE :anio >= a.fechaNacimiento AND :anio < a.fechaDefuncion")
    List<AutorDTO> obtenerAutoresPorAnio(Integer anio);

    //5.Listar libros por idioma
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> obtenerLibrosPorIdioma(Idiomas idioma);


    Optional<Libro> findByTitulo(String titulo);
}
