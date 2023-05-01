package com.qapaq.si.background.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.si.background.jpa.model.AuditoriaParametro;

public interface AuditoriaParametroRepositorio extends JpaRepository<AuditoriaParametro, Long> {

    /**
     * Metodo para buscar un objeto por id.
     *
     * @param id
     * @return
     */
    public AuditoriaParametro findByIdAuditoriaParametro(Long id);
    
    /**
     * Metodo para buscar todos los objetos.
     *
     * @return
     */
    public List<AuditoriaParametro> findAll();
    
    /**
     * Metodo para buscar un objeto por nombre.
     * 
     * @param nombre
     * @return
     */
    public AuditoriaParametro findByNombre(String nombre);
    
}  