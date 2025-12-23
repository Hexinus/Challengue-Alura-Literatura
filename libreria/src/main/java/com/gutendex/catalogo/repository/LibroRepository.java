package com.gutendex.catalogo.repository; //repositorio de libros

import com.gutendex.catalogo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);

    List<Libro> findByIdioma(String idioma);
}
