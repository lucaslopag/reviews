package com.articulos.articulo_service.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
}