package com.articulos.articulo_service.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;
import com.articulos.articulo_service.model.Producto;
import com.articulos.articulo_service.DTO.ProductoDTO;
import com.articulos.articulo_service.service.ArticuloService;
import com.articulos.articulo_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ArticuloService articuloService;
    public ProductoController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping("/productos")
    public List<ProductoDTO> getAllProductos() {
        return articuloService.getAllProductos();
    }

    @PostMapping("/productos")
    public ProductoDTO saveProducto(@RequestBody ProductoDTO productoDTO) {
        return articuloService.save(productoDTO);
    }

    @GetMapping("/productos/{id}")
    public ProductoDTO getProducto(@PathVariable Long id) {
        return articuloService.getProducto(id);
    }

    @PutMapping("/productos/{id}")
    public ProductoDTO updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return articuloService.updateProducto(id, productoDTO);
    }

    @DeleteMapping("/productos/{id}")
    public String deleteProducto(@PathVariable Long id) {
        return articuloService.deleteProducto(id);
    }

}
