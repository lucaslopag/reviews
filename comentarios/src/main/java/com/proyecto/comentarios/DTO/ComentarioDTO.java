package com.proyecto.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private String id;
    private String articuloId;
    private String usuario;
    private String texto;
    private Integer estrellas;
}