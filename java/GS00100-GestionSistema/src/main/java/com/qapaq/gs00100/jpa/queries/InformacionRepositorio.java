package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.Informacion;

/**
 * Interface para la clase Informacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Repository
public interface InformacionRepositorio extends JpaRepository<Informacion, Long> {

    /**
     * Metodo para buscar un objeto por id.
     * 
     * @param id
     * @return
     */
    public Informacion findByIdInformacion(Long idInformacion);

    /**
     * Metodo para buscar todos los objetos.
     * 
     * @return
     */
    public List<Informacion> findAll();

    /**
     * Metodo para buscar un objeto por nombre.
     * 
     * @param nombre
     * @return
     */
    public Informacion findByNombre(String nombre);
}
