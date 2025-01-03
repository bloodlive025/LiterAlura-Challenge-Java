package com.example.aluracursos.LiterAlura.Principal;

import com.example.aluracursos.LiterAlura.Modelos.Datos;
import com.example.aluracursos.LiterAlura.Modelos.DatosLibros;
import com.example.aluracursos.LiterAlura.Modelos.Libro;
import com.example.aluracursos.LiterAlura.Repository.DatosRepository;
import com.example.aluracursos.LiterAlura.service.ConsumoAPI;
import com.example.aluracursos.LiterAlura.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoApi = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private DatosRepository repositorio;
    public Principal(DatosRepository repositorio){
        this.repositorio=repositorio;
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
               /* case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
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

    public void buscarLibroPorTitulo(){
        System.out.println("Ingrese el titulo del libro: ");
        String tituloLibro=teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ","+"));
        var datos=conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> datosLibro= datos.resultados()
                .stream()
                .findFirst();
        if (datosLibro.isPresent()){
            Libro libro = new Libro(datosLibro.get());
            repositorio.save(libro);
        }else{
            System.out.println("Libro no encontrado");
        }

    }
}
