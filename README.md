Catálogo de Libros – Gutendex API

Aplicación de consola desarrollada en Java + Spring Boot que consume la API pública Gutendex para buscar libros del Proyecto Gutenberg, almacenarlos en una base de datos y realizar consultas sobre ellos.

 Funcionalidades

La aplicación cumple con los siguientes requisitos:

1- Buscar libro por título

El usuario ingresa el nombre del libro.

La aplicación consulta la API Gutendex.

Se guarda el libro en la base de datos con:

2- Título

Autor (Apellido, Nombre)

Idioma (EN, ES, FR, PT)

Número de descargas

3- Listar autores registrados

Muestra todos los autores almacenados en la base de datos.

4- Buscar libros por idioma

El usuario ingresa un idioma (ES, EN, FR, PT).

Se listan los libros disponibles en ese idioma.

5- Manejo de búsquedas inválidas

Si no se encuentra información, el sistema notifica al usuario.

 Evitar duplicados

No permite guardar el mismo libro más de una vez.

La App se corre desde package com.gutendex.catalogo;
