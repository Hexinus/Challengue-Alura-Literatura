package com.gutendex.catalogo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutendex.catalogo.dto.GutendexResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class GutendexService { //API librelia online utilizada

    private static final String BASE_URL = "https://gutendex.com/books/?search=";

    public GutendexResponse buscarLibro(String titulo) {

        try {
            String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            URI uri = URI.create(BASE_URL + encodedTitulo);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), GutendexResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar libro", e);
        }
    }
}
