package com.example.aluracursos.LiterAlura.Principal;

import com.example.aluracursos.LiterAlura.Modelos.Autor;
import com.example.aluracursos.LiterAlura.Modelos.Datos;
import com.example.aluracursos.LiterAlura.Modelos.DatosLibros;
import com.example.aluracursos.LiterAlura.Modelos.Libro;
import com.example.aluracursos.LiterAlura.Repository.AutorRepository;
import com.example.aluracursos.LiterAlura.Repository.DatosRepository;
import com.example.aluracursos.LiterAlura.service.ConsumoAPI;
import com.example.aluracursos.LiterAlura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoApi = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private DatosRepository repositorio;
    private AutorRepository autorRepositorio;
    public Principal(DatosRepository repositorio,AutorRepository autorRepositorio){
        this.repositorio=repositorio;
        this.autorRepositorio=autorRepositorio;
    }

    int opcion;

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opcion a traves de su numero:
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar Autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            try {
                System.out.println(menu);
                opcion = Integer.parseInt(teclado.nextLine()); // Leer como texto y convertir a entero
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                continue; // Volver al inicio del bucle
            }


            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                /*case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;*/
                case 0:
                    System.out.println("Cerrando la aplicacion");
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }

    }

    public void verificarLibroEnBilioteca(Libro libro){
        String tituloLibro = libro.getTitulo();
    }

    public void buscarLibroPorTitulo(){
        System.out.println("Ingrese el titulo del libro: ");
        String tituloLibro=teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ","+"));
        var datos=conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> datosLibro= datos.resultados()
                .stream()
                .findFirst();
        try {
            if (datosLibro.isPresent()) {
                Libro libro = new Libro(datosLibro.get());
                System.out.println(libro.getAuthorDatos().getFechaDeNacimiento());
                if (repositorio.buscarAutor(libro.getAutor()).isEmpty()) {
                    System.out.println("El real gaaaa");
                    autorRepositorio.save(libro.getAuthorDatos());
                    repositorio.save(libro);
                    System.out.println("GAAAA");
                    System.out.println("Libro Agregado a la base de datos");
                    System.out.println(datosLibro);
                } else {
                    System.out.println("GG");
                    libro.setAuthorDatos(repositorio.buscarAutor(libro.getAutor()).get(0).getAuthorDatos());
                    repositorio.save(libro);
                }

            } else {
                System.out.println("Libro no encontrado");
            }

        }
        catch (DataIntegrityViolationException e){
            System.out.println("El libro ya se encuentra en la base de datos");
        }
    }

    public void listarLibrosRegistrados(){
        System.out.println(repositorio.findAll());

    }

    public void listarAutoresRegistrados(){
        System.out.println(autorRepositorio.findAll());
    }



}
