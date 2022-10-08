package com.qapaq.si.background.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si.background.jpa.model.Parametro;

/**
 * Clase para dar repositorio para spring del jpa Parametro.java   
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020/08/14
 * 
 */
@Repository
public interface ParametroRepositorio extends JpaRepository <Parametro, Long> {
    
    /**
     * Metodo para buscar un objeto por id.
     *
     * @param id
     * @return
     */
    public Parametro findByIdParametro(Long id);
    
    /**
     * Metodo para buscar todos los objetos.
     */
    public List<Parametro> findAll();
    
    /**
     * Metodo para buscar un objeto por indice y por id_modulo
     *
     */
    /* */
    public Parametro findByIndiceAndIdModulo(String indice, Long idModulo);
}