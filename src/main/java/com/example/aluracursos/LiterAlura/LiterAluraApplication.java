package com.example.aluracursos.LiterAlura;

import com.example.aluracursos.LiterAlura.Principal.Principal;
import com.example.aluracursos.LiterAlura.Repository.DatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}
	@Autowired
	DatosRepository repositorio ;

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.muestraElMenu();

	}
}
