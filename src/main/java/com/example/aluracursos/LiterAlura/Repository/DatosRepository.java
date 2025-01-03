package com.example.aluracursos.LiterAlura.Repository;

import com.example.aluracursos.LiterAlura.Modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosRepository extends JpaRepository<Libro,Long> {
}
