package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.Modulo;

/**
 * Clase para la interfaz de la clase Modulo.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-10
 * 
 */
@Repository
public interface ModuloRepositorio extends JpaRepository<Modulo, Long> {

        /**
         * Metodo para buscar un objeto por id y con estado distinto a eliminado (X).
         * 
         * @param id
         * @return
         * 
         */
        @Query("SELECT m FROM Modulo m WHERE m.idModulo = ?1 AND m.estado <> 'X'")
        public Modulo findByIdModulo(Long id);

        /**
         * Metodo para buscar un objeto por indice y con estado distinto a eliminado (X)
         */
        @Query("SELECT m FROM Modulo m WHERE m.indice = ?1 AND m.estado <> 'X'")
        public Modulo findByIndiceAndEstadoNot(String indice);

        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        @Query("SELECT m FROM Modulo m WHERE m.estado <> 'X'")
        public List<Modulo> findAll();

        /**
         * Metodo para buscar un objeto por nombre.
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT m FROM Modulo m WHERE m.nombre = ?1 AND m.estado <> 'X'")
        public Modulo findByNombre(String nombre);

        /**
         * Metodo para buscar un objeto por indice.
         * 
         * @param indice
         * @return
         */
        @Query("SELECT m FROM Modulo m WHERE m.indice = ?1 AND m.estado <> 'X'")
        public Modulo findByIndice(String indice);

        /**
         * Metodo para borrar el objeto por id en forma logica (cambio de estado a X).
         * 
         */
        @Query("UPDATE Modulo m SET m.estado = 'X' WHERE m.idModulo = ?1")
        public void deleteByIdModulo(Long id);

        

}
