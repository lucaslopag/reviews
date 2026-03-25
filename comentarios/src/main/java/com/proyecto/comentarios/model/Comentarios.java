package com.proyecto.comentarios.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "comentarios")
public class Comentarios {
    @Id
    private String id;
    private String articuloId;
    private String usuario;
    private String texto;
    private Integer estrellas;
}