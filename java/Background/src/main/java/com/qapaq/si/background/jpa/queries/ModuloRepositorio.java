package com.qapaq.si.background.jpa.queries;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.qapaq.si.background.jpa.model.Modulo;

/**
 * Clase para dar repositorio para spring del jpa Modulo.java   
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020/08/16
 * 
 */
public interface ModuloRepositorio extends JpaRepository<Modulo, Long> {
    
    /**
     * Metodo para buscar un objeto por id.
     *
     * @param id
     * @return
     */
    public Modulo findByIdModulo(Long id);
    
    /**
     * Metodo para buscar todos los objetos.
     */
    public List<Modulo> findAll();
    
    /**
     * Metodo para buscar un objeto por indice.
     * 
     * @param indice
     * @return
     */
     public Modulo findByIndice(String indice);
}
