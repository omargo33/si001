package com.qapaq.ar00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ar00100.jpa.model.Informacion;
import com.qapaq.ar00100.jpa.queries.InformacionRepositorio;

/**
 * Clase de servicio CRUD para Acceso Repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-11-25
 * 
 */
@Service
@Transactional("ar001001TransactionManager")
public class InformacionServicio {

    private InformacionRepositorio informacionRepositorio;


    @Autowired
    public InformacionServicio(InformacionRepositorio informacionRepositorio) {
        this.informacionRepositorio = informacionRepositorio;
    }

    /**
     * Metodo para listar entre fechas paginadas.
     * 
     * @param fechaInicio
     * @param fechaFin
     * @param pageable
     */
    public List<Informacion> listInformacionBetweenDatesPaginated(Date fechaInicio, Date fechaFin, Pageable pageable) {
        return informacionRepositorio.findByFechaBetween(fechaInicio, fechaFin, pageable);
    }

    /**
     * Metodo para buscar por id_direccion.
     * 
     * @param idDireccion
     */
    public Informacion findInformacionById(Long idInformacion) {
        return informacionRepositorio.findByIdInformacion(idInformacion);
    }
}
