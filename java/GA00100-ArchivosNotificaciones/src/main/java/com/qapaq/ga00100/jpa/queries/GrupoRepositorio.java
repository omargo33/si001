package com.qapaq.ga00100.jpa.queries;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.qapaq.ga00100.jpa.model.Grupo;
/**
 * Interfaces para CRUD de JPA Archivo.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface GrupoRepositorio extends PagingAndSortingRepository<Grupo, Long> {

    /**
     * Metodo para obtener un grupo por id_grupo.
     */
    Grupo findByIdGrupo(Long idGrupo);

    /**
     * Metodo para obtener un grupo por id, esquema y tabla.
     */
    Grupo findByIdAndEsquemaAndTabla(Long id, String esquema, String tabla);
    
}
