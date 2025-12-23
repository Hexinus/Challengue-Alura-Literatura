package com.gutendex.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; //libreria para convertir JSONs

@JsonIgnoreProperties(ignoreUnknown = true) //Ignorar JSON con más campos de los que admite mi clase
public class GutendexAuthor {

    private String name; //crear variable nombre tipo string

    public GutendexAuthor() { //declaración de la clase
    }

    public String getName() { //getter del name
        return name;
    }

    public void setName(String name) { //setter del name.

        this.name = name;
    }
}