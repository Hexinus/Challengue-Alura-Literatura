package com.gutendex.catalogo; //programa principal, el proyecto se corre desde aqui

//importar restos de paquetes para ejecutar

import com.gutendex.catalogo.service.CatalogoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner; //leer la respuesta de usuario

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

    private final CatalogoService catalogoService;

    public CatalogoApplication(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {  //menu visible para el usuario

            System.out.println("""
                ================================
                📚 CATÁLOGO DE LIBROS - GUTENDEX
                ================================
                1️⃣ Buscar libro por título
                2️⃣ Listar libros registrados
                3️⃣ Listar autores
                4️⃣ Buscar libros por idioma
                0️⃣ Salir
                ================================
                """);

            System.out.print(" Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // usuario introduce opcion

            // Interruptor de las funciones del programa

            switch (opcion) {
                case 1 -> {
                    System.out.print("📖 Ingresa el título del libro: ");
                    String titulo = scanner.nextLine();
                    catalogoService.buscarYGuardarLibro(titulo);
                }
                case 2 -> catalogoService.listarLibros();
                case 3 -> catalogoService.listarAutores();
                case 4 -> {
                    System.out.print("🌍 Ingresa el idioma (ej: en, es, fr): ");
                    String idioma = scanner.nextLine();
                    catalogoService.buscarPorIdioma(idioma);
                }
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida");
            }
        }
    }
}
