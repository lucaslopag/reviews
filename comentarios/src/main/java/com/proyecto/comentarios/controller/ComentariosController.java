package com.proyecto.comentarios.controller;

import com.proyecto.comentarios.model.Comentarios;
import com.proyecto.comentarios.repository.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-one")
public class ComentariosController {

    @Autowired
    private ComentariosRepository repository;

    @GetMapping("/comentarios")
    public List<Comentarios> listar() {
        return repository.findAll();
    }

    @PostMapping("/comentarios")
    public Comentarios guardar(@RequestBody Comentarios comentario) {
        return repository.save(comentario);
    }
    
}