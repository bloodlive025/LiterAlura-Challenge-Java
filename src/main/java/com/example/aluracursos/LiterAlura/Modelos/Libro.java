package com.example.aluracursos.LiterAlura.Modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Biblioteca")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private List<String> languages;
    private Double countDownloads;  
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor authorDatos;
    public Libro(){};

    public Libro(DatosLibros libros){
        this.titulo=libros.titulo();
        this.autor=libros.autor().stream()
                .findFirst()
                .map(a->a.nombre())
                .orElse("Nombre no disponible");
        this.languages = libros.idiomas();
        this.countDownloads=libros.numeroDeDescargas();
        this.authorDatos=libros.autor().stream()
                .findFirst()
                .map(a->new Autor(a))
                .orElse(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getCountDownloads() {
        return countDownloads;
    }

    public void setCountDownloads(Double countDownloads) {
        this.countDownloads = countDownloads;
    }

    public Autor getAuthorDatos() {
        return authorDatos;
    }

    public void setAuthorDatos(Autor authorDatos) {
        this.authorDatos = authorDatos;
    }

    @Override
    public String toString() {
        return "==================================\n"+
                "Libro numero" +id +
                "\n titulo= " + titulo + '\n' +
                "Autor: " + autor + '\n' +
                "Languages: " + languages + '\n'+
                "CountDownloads: " + countDownloads +
                "\n=================================\n";
    }
}
