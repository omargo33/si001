package com.qapaq.si.background.jpa.queries;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.si.background.jpa.model.Clima;

/**
 * Clase para dar repositorio para spring del jpa Clima.java
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020/08/14
 * 
 */
public interface ClimaRepositorio extends JpaRepository<Clima, Long> {

    /**
     * Metodo para buscar un objeto por id.
     * 
     */
    public Clima findByIdClima(Long id);

    /**
     * Metodo para buscar todos los objetos.
     * 
     */
    public List<Clima> findAll();

    /**
     * Metodo para buscar por id de ciudad y un rango de usuario_fecha.
     * 
     */
    public List<Clima> findByIdCiudadAndUsuarioFechaBetween(Long idCiudad, String usuarioFechaInicio,
            String usuarioFechaFin);

}