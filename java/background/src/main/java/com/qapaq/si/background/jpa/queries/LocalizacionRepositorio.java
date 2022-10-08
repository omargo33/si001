package com.qapaq.si.background.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qapaq.si.background.jpa.model.Localizacion;
import org.springframework.stereotype.Repository;

/**
 * Class para dar repositorio para spring del jpa Localizacion.java
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020/08/14
 * 
 */
@Repository
public interface LocalizacionRepositorio extends JpaRepository<Localizacion, Long> {

    /**
     * Metodo para buscar un objeto por id.
     */
    public Localizacion findByIdLocalizacion(Long id);
    
    /**
     * Metodo para buscar todos los objetos.
     */
    public List<Localizacion> findAll();
}
