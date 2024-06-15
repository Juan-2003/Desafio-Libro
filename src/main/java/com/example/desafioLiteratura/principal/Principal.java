package com.example.desafioLiteratura.principal;

import com.example.desafioLiteratura.dto.AutorDTO;
import com.example.desafioLiteratura.dto.LibroDTO;
import com.example.desafioLiteratura.model.*;
import com.example.desafioLiteratura.repository.LibroRepository;
import com.example.desafioLiteratura.service.ConsultarAPI;
import com.example.desafioLiteratura.service.MapearDatos;

import java.lang.reflect.Array;
import java.util.*;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/?";
    private final String URL_SEARCH = URL_BASE + "search=";
    Scanner scanner = new Scanner(System.in);
    private LibroRepository repository;
    public Principal(LibroRepository repository){
        this.repository = repository;
    }

    public void iniciar(){
        int opcion;
        do{
            var menu = """
                1.Buscar libro por titulo
                2.Listar libros registrados
                3.Listar autores registrados
                4.Listar autores vivos en un determinado año
                5.Listar libros por idioma
                0.Salir
                Opcion: 
                """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion){
                case 1: buscarLibrosPorTitulo();
                    break;
                case 2: buscarLibrosRegistrados();
                    break;
                case 3: buscarAutoresRegistrados();
                    break;
                case 4: buscarAutoresVivosPorAnio();
                    break;
                case 5: buscarLibrosPorIdioma();
                    break;
                case 0: System.out.println("SALIENDO...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        }while(opcion != 0);
    }

    private void buscarLibrosPorTitulo(){
        ApiResultados apiResultados = getDatosLibro();
        LibroDTO libroDTO = new LibroDTO(apiResultados.titulo(),
                                        Idiomas.fromApi(apiResultados.idiomas().get(0)),
                                        apiResultados.descargas(),
                                        apiResultados.autores().get(0).nombre());
        mostrarLibroDTO(libroDTO);
    }

    private void buscarLibrosRegistrados(){
        List<LibroDTO> librosRegistrados = repository.obtenerLibrosRegistrados();
        librosRegistrados.stream().forEach(this::mostrarLibroDTO);
    }

    private void buscarAutoresRegistrados(){
        List<AutorDTO> autoresRegistrados = repository.obtenerAutoresRegistrados();
        autoresRegistrados.stream().forEach(this::mostrarAutorDTO);
    }

    private void buscarAutoresVivosPorAnio(){
        System.out.println("Ingresa el año: ");
        Integer anio = scanner.nextInt();

        List<AutorDTO> autores=  repository.obtenerAutoresPorAnio(anio);
        autores.stream().forEach(this::mostrarAutorDTO);
    }

    private void buscarLibrosPorIdioma(){
        System.out.println("Ingresa el idioma: ");
        String idioma = scanner.nextLine();

        repository.obtenerLibrosPorIdioma(Idiomas.fromEspanol(idioma)).stream()
                .forEach(System.out::println);
    }

    private ApiResultados getDatosLibro(){
        System.out.println("Ingresa el nombre del libro: ");
        String nombreLibro = scanner.nextLine();
        String url = URL_SEARCH + nombreLibro.replace(" ", "+");
        String json = ConsultarAPI.consultarApi(url);
        ApiData apiData = mapearJson(json, ApiData.class);
        ApiResultados apiResultados = apiData.resultados().get(0);
        agregarBD(apiResultados);
        return apiResultados;
    }

    private <T> T mapearJson(String json, Class<T>clase){
        return MapearDatos.mapearDatos(json, clase);
    }

    private void agregarBD(ApiResultados apiResultados) {
        Optional<Libro> libro = repository.findByTitulo(apiResultados.titulo());

        if (libro.isEmpty()) {
            repository.save(new Libro(apiResultados));
            System.out.println("Registro exitoso!");
        } else {
            System.out.println("El libro ingresado ya registrado.");
        }

    }

    private void mostrarLibroDTO(LibroDTO libroDTO){
        System.out.println( "----- LIBRO -----" + "\n" +
                "Titulo: " + libroDTO.titulo() + "\n" +
                "Autor: " + libroDTO.autores() + "\n" +
                "Idioma: " + libroDTO.idioma() + "\n" +
                "Numero Descargas: " + libroDTO.descargas() + "\n" +
                "------------------");

    }

    private void mostrarAutorDTO(AutorDTO autorDTO){
        System.out.println("Autor: "+ autorDTO.nombre() + "\n" +
                "Fecha nacimiento: " + autorDTO.fechaNacimiento() + "\n" +
                "Fecha de fallecimiento: " + autorDTO.fechaDefuncion() + "\n" +
                "Libros: " + autorDTO.libros() + "\n");
    }

    private void limipiarBuffer(){
        if(scanner.hasNext()){
            scanner.nextLine();
        }
    }





}
