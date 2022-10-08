package com.qapaq.si.background.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si.background.jpa.model.AuditoriaEvento;

/**
 * Clase para dar repositorio para spring del jpa AuditoriaEvento.java
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020-08-22
 * 
 */
@Repository
public interface AuditoriaEventoRepositorio extends JpaRepository<AuditoriaEvento, Long> {
    
    /**
     * Metodo para buscar un objeto por id.
     */
    public AuditoriaEvento findByIdAuditoriaEvento(Long id);
    
    /**
     * Metodo para buscar todos los objetos.
     * 
     */
    public List<AuditoriaEvento> findAll();
    
    /**
     * Metodo para buscar un objeto por nombre.
     * 
     */
    public AuditoriaEvento findByNombre(String nombre);
}
