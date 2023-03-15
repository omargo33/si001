package com.qapaq.lg00100.servicio;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.lg00100.jpa.model.Auditoria;
import com.qapaq.lg00100.jpa.model.AuditoriaEvento;
import com.qapaq.lg00100.jpa.model.AuditoriaParametro;
import com.qapaq.lg00100.jpa.queries.AuditoriaEventoRepositorio;
import com.qapaq.lg00100.jpa.queries.AuditoriaParametroRepositorio;
import com.qapaq.lg00100.jpa.queries.AuditoriaRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Clase de servicio CRUD para Auditoria completa.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-11-25
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuditoriaServicio {

    @Autowired
    private AuditoriaRepositorio auditoriaRepositorio;
    @Autowired
    private AuditoriaParametroRepositorio auditoriaParametroRepositorio;
    @Autowired
    private AuditoriaEventoRepositorio auditoriaEventoRepositorio;

    private Auditoria auditoria;

    private int orden;

    
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
     * Metodo para agregar un evento y de ser muy extenso crea multiples eventos.
     * 
     * @param descripcion
     * @param tipo
     * 
     */
    public void agregarEnvento(String descripcion, String tipo) {       
        String[] results = descripcion.split("(?<=\\G.{512})");       

        for (int i = 0; i < results.length; i++) {           
            auditoriaEventoRepositorio.save(crearAuditoriaEvento (results[i], tipo, i));            
        }
        orden++;
    }

    /**
     * Metodo para crear un evento.
     * 
     * @param descripcion
     * @param tipo
     * @param i
     * @return
     */
    private AuditoriaEvento crearAuditoriaEvento (String descripcion, String tipo, int i){
        AuditoriaEvento auditoriaEvento = new AuditoriaEvento();
        auditoriaEvento.setIdAuditoria(auditoria.getIdAuditoria());
        auditoriaEvento.setUsuarioFecha(new Date());
        auditoriaEvento.setTipo(StringUtils.truncate(tipo,8));
        auditoriaEvento.setOrden(orden);
        auditoriaEvento.setDescripcion(descripcion);
        auditoriaEvento.setParte(i);
        return auditoriaEvento;
    }

    /**
     * Metodo para agregar un parametro.
     * 
     * @param nombre
     * @param valor
     * @param direccion
     * 
     */
    public void agregarParametro(String nombre, String valor, String direccion) {
        AuditoriaParametro auditoriaParametro = new AuditoriaParametro();
        auditoriaParametro.setIdAuditoria(auditoria.getIdAuditoria());
        auditoriaParametro.setNombre(StringUtils.truncate(nombre,128));
        auditoriaParametro.setValor(StringUtils.truncate(valor, 256));
        auditoriaParametro.setDireccion(StringUtils.truncate(direccion, 8));
        auditoriaParametroRepositorio.save(auditoriaParametro);        
    }
}
