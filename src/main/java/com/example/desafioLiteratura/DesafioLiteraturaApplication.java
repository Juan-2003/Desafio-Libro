package com.example.desafioLiteratura;

import com.example.desafioLiteratura.model.Libro;
import com.example.desafioLiteratura.principal.Principal;
import com.example.desafioLiteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraturaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.iniciar();
	}
}
