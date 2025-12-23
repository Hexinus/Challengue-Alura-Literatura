package com.gutendex.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; //importan bibliotecas
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexBook { //declarar clase

    private String title;
    private List<GutendexAuthor> authors;
    private List<String> languages;

    @JsonProperty("download_count")
    private Integer downloadCount;

    public String getTitle() {
        return title;
    } //sacamos información del libro

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }
}
