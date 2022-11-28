package com.qapaq.ar00100.servicio;

import java.util.Date;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ar00100.jpa.queries.AccesoRepositorio;

import com.qapaq.ar00100.jpa.model.Acceso;

/**
 * Clase de servicio CRUD para Acceso Repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-11-25
 * 
 */
@Service
@Transactional("ar001001TransactionManager")
public class AccesoServicio {

    @Autowired
    private AccesoRepositorio accesoRepositorio;

    /**
     * Metodo para insertar un nuevo dato.
     * 
     */
    public Acceso insertAcceso(Acceso acceso) {
        return accesoRepositorio.insertAcceso(
                StringUtils.truncate(acceso.getNombre(), 256),
                StringUtils.truncate(acceso.getToken(), 256),
                StringUtils.truncate(acceso.getTokenApi(), 2048),
                acceso.getUsuarioFecha(),
                StringUtils.truncate(acceso.getUsuarioPrograma(), 256));
    }

    /**
     * Metodo para listar entre fechas paginadas.
     * 
     * @param fechaInicio
     * @param fechaFin
     * @param pageable
     */
    public List<Acceso> listAccesoBetweenDatesPaginated(Date fechaInicio, Date fechaFin, Pageable pageable) {
        return accesoRepositorio.findByUsuarioFechaBetween( fechaInicio,  fechaFin,  pageable);
    }

    /**
     * Metodo para buscar por id_acceso.
     * 
     * @param idAcceso
     */
    public Acceso findByIdAcceso(Long idAcceso) {
        return accesoRepositorio.findByIdAcceso(idAcceso);
    }
}