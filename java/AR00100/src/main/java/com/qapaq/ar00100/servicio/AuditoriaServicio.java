package com.qapaq.ar00100.servicio;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ar00100.jpa.model.Auditoria;
import com.qapaq.ar00100.jpa.model.AuditoriaEvento;
import com.qapaq.ar00100.jpa.model.AuditoriaParametro;
import com.qapaq.ar00100.jpa.queries.AuditoriaEventoRepositorio;
import com.qapaq.ar00100.jpa.queries.AuditoriaParametroRepositorio;
import com.qapaq.ar00100.jpa.queries.AuditoriaRepositorio;

/**
 * Clase de servicio CRUD para Auditoria completa.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-11-25
 * 
 */
@Service
@Transactional("ar001001TransactionManager")
public class AuditoriaServicio {

    private AuditoriaRepositorio auditoriaRepositorio;

    private AuditoriaParametroRepositorio auditoriaParametroRepositorio;

    private AuditoriaEventoRepositorio auditoriaEventoRepositorio;

    private Auditoria auditoria;

    private int orden;

    @Autowired
    public AuditoriaServicio(AuditoriaRepositorio auditoriaRepositorio,
            AuditoriaParametroRepositorio auditoriaParametroRepositorio,
            AuditoriaEventoRepositorio auditoriaEventoRepositorio) {
        this.auditoriaRepositorio = auditoriaRepositorio;
        this.auditoriaParametroRepositorio = auditoriaParametroRepositorio;
        this.auditoriaEventoRepositorio = auditoriaEventoRepositorio;
    }

    /**
     * Metodo para crear una nueva auditoria.
     * 
     * @param nombre
     * @param valor01
     * @param valor02
     * @param elemento
     * @param usuario
     * @param usuarioPrograma
     * @return
     */
    public Auditoria createAuditoria(String nombre, String valor01, String valor02, String elemento, String usuario,
            String usuarioPrograma) {
        auditoria = new Auditoria();
        auditoria.setNombre(StringUtils.truncate(nombre, 128));
        auditoria.setValor01(StringUtils.truncate(valor01, 256));
        auditoria.setValor02(StringUtils.truncate(valor02, 256));
        auditoria.setElemento(StringUtils.truncate(elemento, 64));
        auditoria.setUsuario(StringUtils.truncate(usuario, 128));
        auditoria.setUsuarioFecha(new Date());
        auditoria.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        auditoriaRepositorio.save(auditoria);
        orden = 0;
        return auditoria;
    }

    /**
     * Metodo para agregar un evento.
     * 
     * @param descripcion
     * @param tipo
     * 
     */
    public void agregarEnvento(String descripcion, String tipo) {
        AuditoriaEvento auditoriaEvento = new AuditoriaEvento();
        auditoriaEvento.setIdAuditoria(auditoria.getIdAuditoria());
        auditoriaEvento.setUsuarioFecha(new Date());
        auditoriaEvento.setTipo(StringUtils.truncate(tipo,8));
        auditoriaEvento.setOrden(orden);

        String[] results = descripcion.split("(?<=\\G.{512})");

        for (int i = 0; i < results.length; i++) {
            auditoriaEvento.setDescripcion(descripcion);
            auditoriaEvento.setParte(String.valueOf(i + 1));
            auditoriaEventoRepositorio.save(auditoriaEvento);
            auditoriaEvento.setIdAuditoriaEvento(0L);
        }
        orden++;
    }

    /**
     * Metodo para agregar un parametro.
     * 
     * @param nombre
     * @param valor
     * @param direccion
     * 
     */
    public void agregarParametro(String nombre, String valor, String direccion ) {
        AuditoriaParametro auditoriaParametro = new AuditoriaParametro();

        auditoriaParametro.setIdAuditoria(auditoria.getIdAuditoria());
        auditoriaParametro.setNombre(StringUtils.truncate(nombre,128));
        auditoriaParametro.setValor(StringUtils.truncate(valor, 256));
        auditoriaParametro.setDireccion(StringUtils.truncate(direccion, 8));

        auditoriaParametroRepositorio.save(auditoriaParametro);        
    }
}
