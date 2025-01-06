package com.example.aluracursos.LiterAlura.Repository;

import com.example.aluracursos.LiterAlura.Modelos.Autor;
import com.example.aluracursos.LiterAlura.Modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatosRepository extends JpaRepository<Libro,Long> {
    @Query("Select l from Libro l where l.autor = :nombreAutor")
    List<Libro> buscarAutor(String nombreAutor);
}
