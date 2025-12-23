package com.gutendex.catalogo.repository; //repositorio de autores

import com.gutendex.catalogo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByApellidoAndNombre(String apellido, String nombre);
}
