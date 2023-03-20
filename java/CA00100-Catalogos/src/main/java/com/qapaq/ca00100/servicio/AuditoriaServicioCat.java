package com.qapaq.ca00100.servicio;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ca00100.jpa.model.AuditoriaCat;
import com.qapaq.ca00100.jpa.model.AuditoriaEventoCat;
import com.qapaq.ca00100.jpa.model.AuditoriaParametroCat;
import com.qapaq.ca00100.jpa.queries.AuditoriaEventoRepositorioCat;
import com.qapaq.ca00100.jpa.queries.AuditoriaParametroRepositorioCat;
import com.qapaq.ca00100.jpa.queries.AuditoriaRepositorioCat;

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
public class AuditoriaServicioCat {

    @Autowired
    private AuditoriaRepositorioCat auditoriaRepositorioCat;

    @Autowired
    private AuditoriaParametroRepositorioCat auditoriaParametroRepositorioCat;
    
    @Autowired
    private AuditoriaEventoRepositorioCat auditoriaEventoRepositorioCat;

    private AuditoriaCat auditoriaCat;

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
    public AuditoriaCat createAuditoria(String nombre, String valor01, String valor02, String elemento, String usuario,
            String usuarioPrograma) {
        auditoriaCat = new AuditoriaCat();
        auditoriaCat.setNombre(StringUtils.truncate(nombre, 128));
        auditoriaCat.setValor01(StringUtils.truncate(valor01, 256));
        auditoriaCat.setValor02(StringUtils.truncate(valor02, 256));
        auditoriaCat.setElemento(StringUtils.truncate(elemento, 64));
        auditoriaCat.setUsuario(StringUtils.truncate(usuario, 128));
        auditoriaCat.setUsuarioFecha(new Date());
        auditoriaCat.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        auditoriaRepositorioCat.save(auditoriaCat);
        orden = 0;
        return auditoriaCat;
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
            auditoriaEventoRepositorioCat.save(crearAuditoriaEvento (results[i], tipo, i));            
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
    private AuditoriaEventoCat crearAuditoriaEvento (String descripcion, String tipo, int i){
        AuditoriaEventoCat auditoriaEventoCat = new AuditoriaEventoCat();
        auditoriaEventoCat.setIdAuditoria(auditoriaCat.getIdAuditoria());
        auditoriaEventoCat.setUsuarioFecha(new Date());
        auditoriaEventoCat.setTipo(StringUtils.truncate(tipo,8));
        auditoriaEventoCat.setOrden(orden);
        auditoriaEventoCat.setDescripcion(descripcion);
        auditoriaEventoCat.setParte(i);
        return auditoriaEventoCat;
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
        AuditoriaParametroCat auditoriaParametroCat = new AuditoriaParametroCat();
        auditoriaParametroCat.setIdAuditoria(auditoriaCat.getIdAuditoria());
        auditoriaParametroCat.setNombre(StringUtils.truncate(nombre,128));
        auditoriaParametroCat.setValor(StringUtils.truncate(valor, 256));
        auditoriaParametroCat.setDireccion(StringUtils.truncate(direccion, 8));
        auditoriaParametroRepositorioCat.save(auditoriaParametroCat);        
    }
}
