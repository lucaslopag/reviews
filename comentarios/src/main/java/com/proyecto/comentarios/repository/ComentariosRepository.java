package com.proyecto.comentarios.repository;

import com.proyecto.comentarios.model.Comentarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends MongoRepository<Comentarios, String> {
}