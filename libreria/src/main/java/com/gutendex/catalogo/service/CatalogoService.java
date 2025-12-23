package com.gutendex.catalogo.service;

import com.gutendex.catalogo.dto.GutendexBook;
import com.gutendex.catalogo.dto.GutendexResponse;
import com.gutendex.catalogo.model.Autor;
import com.gutendex.catalogo.model.Libro;
import com.gutendex.catalogo.repository.AutorRepository;
import com.gutendex.catalogo.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoService {

    private final GutendexService gutendexService;
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

        public CatalogoService(GutendexService gutendexService,
                           AutorRepository autorRepository,
                           LibroRepository libroRepository) {
        this.gutendexService = gutendexService;
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    // buscar y guardar libro
    public void buscarYGuardarLibro(String titulo) {

        GutendexResponse response = gutendexService.buscarLibro(titulo);

        if (response.getResults().isEmpty()) {
            System.out.println("❌ No se encontró ningún libro");
            return;
        }

        GutendexBook book = response.getResults().get(0);
        guardarLibro(book);
    }

    // guardar libro obtenido de de la API a la base de datos local
    public void guardarLibro(GutendexBook book) {

        String nombreCompleto = book.getAuthors().get(0).getName();
        String[] partes = nombreCompleto.split(", ");
        String apellido = partes[0];
        String nombre = partes.length > 1 ? partes[1] : "";

        Autor autor = autorRepository
                .findByApellidoAndNombre(apellido, nombre)
                .orElseGet(() -> {
                    Autor nuevoAutor = new Autor();
                    nuevoAutor.setApellido(apellido);
                    nuevoAutor.setNombre(nombre);
                    return autorRepository.save(nuevoAutor);
                });

        //Evitar duplicados
        if (libroRepository.findByTitulo(book.getTitle()).isPresent()) {
            System.out.println("⚠️ El libro ya existe en la base de datos");
            return;
        }

        Libro libro = new Libro();
        libro.setTitulo(book.getTitle());
        libro.setIdioma(book.getLanguages().get(0));
        libro.setDescargas(book.getDownloadCount());
        libro.setAutor(autor);

        libroRepository.save(libro);
        System.out.println("✅ Libro guardado correctamente");
    }

    // ✅ lista de libros en base de datatos local
    public void listarLibros() {

        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros registrados");
            return;
        }

        libros.forEach(l -> {
            System.out.println("📘 " + l.getTitulo());
            System.out.println("   Autor: " + l.getAutor().getNombre() + " " + l.getAutor().getApellido());
            System.out.println("   Idioma: " + l.getIdioma());
            System.out.println("   Descargas: " + l.getDescargas());
            System.out.println("----------------------------");
        });
    }

    // lista de autores en base de datos local
    public void listarAutores() {

        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("📭 No hay autores registrados");
            return;
        }

        autores.forEach(a ->
                System.out.println("✍️ " + a.getNombre() + " " + a.getApellido())
        );
    }

    // lista de libros por idioma
    public void buscarPorIdioma(String idioma) {

        List<Libro> libros = libroRepository.findByIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("❌ No se encontraron libros en el idioma " + idioma);
            return;
        }

        libros.forEach(l ->
                System.out.println("📗 " + l.getTitulo() + " (" + l.getIdioma() + ")")
        );
    }
}
