package com.qapaq.si.background.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.si.background.jpa.model.Auditoria;   


public interface AuditoriaRepositorio extends JpaRepository<Auditoria, Long> {
    
    /**
     * Metodo para buscar un objeto por id.
     */
    public Auditoria findByIdAuditoria(Long id);
    
    /**
     * Metodo para buscar todos los objetos.
     * 
     */
    public List<Auditoria> findAll();
    
    /**
     * Metodo para buscar un objeto por nombre.
     * 
     */
    public Auditoria findByNombre(String nombre);
    
}
