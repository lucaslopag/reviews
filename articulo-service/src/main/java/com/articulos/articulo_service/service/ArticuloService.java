package com.articulos.articulo_service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articulos.articulo_service.DTO.ProductoDTO;
import com.articulos.articulo_service.model.Producto;
import com.articulos.articulo_service.repository.ProductoRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArticuloService {

    @Autowired
    private final ProductoRepository productoRepository;

    public ArticuloService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoDTO save(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setName(productoDTO.getName());
        producto.setDescription(productoDTO.getDescription());
        producto.setPrice(productoDTO.getPrice());
        producto = productoRepository.save(producto);
        return mapToDTO(producto);
    }

    public ProductoDTO getProducto(Long id) {
        Producto producto = productoRepository.findById(id).get();
        return mapToDTO(producto);
    }

    public List<ProductoDTO> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id).get();
        producto.setName(productoDTO.getName());
        producto.setDescription(productoDTO.getDescription());
        producto.setPrice(productoDTO.getPrice());
        producto = productoRepository.save(producto);
        return mapToDTO(producto);
    }

    public String deleteProducto(Long id) {
        productoRepository.deleteById(id);
        return "Producto eliminado con exito";
    }

    
    public ProductoDTO mapToDTO (Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setName(producto.getName());
        productoDTO.setDescription(producto.getDescription());
        productoDTO.setPrice(producto.getPrice());
        return productoDTO;
    }

}
