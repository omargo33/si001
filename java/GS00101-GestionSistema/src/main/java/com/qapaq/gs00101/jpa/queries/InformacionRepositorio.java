package com.qapaq.gs00101.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.gs00101.jpa.model.Informacion;

/**
 * Interface para la clase Informacion.
 * 
 * @author o.velez@qapaq.io
 * @since 2022-09-09
 * 
 */
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
