package com.qapaq.si.background.jpa.queries;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qapaq.si.background.jpa.model.Ciudad;

/**
 * Clase para dar repositorio para spring del jpa Ciudad.java   
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020/08/14
 * 
 */
@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad, Long> {
    
    /**
     * Metodo para buscar un objeto por id.
     */
    public Ciudad findByIdCiudad(Long id);

    /**
     * Metodo para buscar todos los objetos.
     * 
     */
    public List<Ciudad> findAll();

    /**
     * Metodo para buscar un objeto por nombre.
     * 
     */
    public Ciudad findByNombre(String nombre);
}
