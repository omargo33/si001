package com.qapaq.seguridades.servicio;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.seguridades.jpa.model.Direccion;
import com.qapaq.seguridades.jpa.queries.DireccionRepositorio;

/**
 * Clase de servicio CRUD para Direccion Repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-11-25
 * 
 */
@Service
@Transactional("ar001001TransactionManager")
public class DireccionServicio {

    @Autowired
    private DireccionRepositorio direccionRepositorio;

    /**
     * Metodo para insertar un nuevo dato.
     * 
     */
    public Direccion saveDireccion(Direccion direccion, String usuario, String usuarioPrograma) {
        direccion.setUsuario(StringUtils.truncate(usuario,128));
        direccion.setUsuarioFecha(new Date());
        direccion.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma,128));
        return direccionRepositorio.save(direccion);
    }
        
    /**
     * Metodo para listar entre fechas paginadas.
     * 
     * @param fechaInicio
     * @param fechaFin
     * @param pageable
     */
    public List<Direccion> listDireccionBetweenDatesPaginated(Date fechaInicio, Date fechaFin, Pageable pageable) {
        return direccionRepositorio.findByUsuarioFechaBetween(fechaInicio, fechaFin, pageable);
    }

    /**
     * Metodo para buscar por id_direccion.
     * 
     * @param idDireccion
     */
    public Direccion findDireccionById(Long idDireccion) {
        return direccionRepositorio.findByIdDireccion(idDireccion);
    }
}