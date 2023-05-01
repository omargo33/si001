package com.qapaq.si.background.servicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si.background.jpa.model.Auditoria;
import com.qapaq.si.background.jpa.model.AuditoriaEvento;
import com.qapaq.si.background.jpa.model.AuditoriaParametro;
import com.qapaq.si.background.jpa.queries.AuditoriaEventoRepositorio;
import com.qapaq.si.background.jpa.queries.AuditoriaRepositorio;
import com.qapaq.si.background.jpa.queries.AuditoriaParametroRepositorio;

/**
 * Clase para dar servicio de auditoria.
 * 
 * @author o.vele@qapaq.io
 * @date 2022-08-22
 * 
 */
@Service
@Transactional
public class AuditoriaServicio {

    private final AuditoriaRepositorio auditoriaRepositorio;
    private final AuditoriaEventoRepositorio auditoriaEventoRepositorio;
    private final AuditoriaParametroRepositorio auditoriaParametroRepositorio;

    /**
     * Constructor de la clase.
     */
    public AuditoriaServicio(AuditoriaRepositorio auditoriaRepositorio,
            AuditoriaEventoRepositorio auditoriaEventoRepositorio,
            AuditoriaParametroRepositorio auditoriaParametroRepositorio) {
        this.auditoriaRepositorio = auditoriaRepositorio;
        this.auditoriaEventoRepositorio = auditoriaEventoRepositorio;
        this.auditoriaParametroRepositorio = auditoriaParametroRepositorio;
    }

    /** 
     * Metodo para crear una auditoria.
     */
    public void crearAuditoria(Auditoria auditoria) {
        auditoriaRepositorio.save(auditoria);
    }

    /**
     * Metodo para crear un evento de auditoria.
     */
    public void crearEvento(AuditoriaEvento evento) {
        auditoriaEventoRepositorio.save(evento);
    }

    /**
     * Metodo para crear un parametro de auditoria.
     */
    public void crearParametro(AuditoriaParametro parametro) {
        auditoriaParametroRepositorio.save(parametro);
    }
}
