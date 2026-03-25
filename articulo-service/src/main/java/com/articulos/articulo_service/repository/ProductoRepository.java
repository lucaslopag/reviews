package com.articulos.articulo_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.articulos.articulo_service.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}