package com.example.aluracursos.LiterAlura.Modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    private String FechaDeNacimiento;
    @OneToMany(mappedBy = "authorDatos" ,fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(DatosAutor datosAutor){
        this.nombre=datosAutor.nombre();
        this.FechaDeNacimiento=datosAutor.fechaDeNacimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return FechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        FechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
    public String toString() {
        return "=================================\n"+
                "Autor: " +
                id +
                "\nnombre='" + nombre  +
                "\nFechaDeNacimiento='" + FechaDeNacimiento + '\'' +
                "\nLibros escritos por el autor: "+
                libros.stream()
                        .map(Libro::getTitulo) // Obtiene el título de cada libro
                        .collect(Collectors.joining(", "))   +
                "\n=================================\n";
    }
}
