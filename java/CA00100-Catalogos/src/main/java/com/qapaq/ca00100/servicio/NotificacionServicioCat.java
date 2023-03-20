package com.qapaq.ca00100.servicio;

import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qapaq.ca00100.ConstantesCA00100;
import com.qapaq.ca00100.jpa.model.NotificacionCat;
import com.qapaq.ca00100.jpa.model.NotificacionParametroCat;
import com.qapaq.ca00100.jpa.queries.NotificacionParametroRepositorioCat;
import com.qapaq.ca00100.jpa.queries.NotificacionRepositorioCat;

import org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-12
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class NotificacionServicioCat {

    @Autowired
    private final NotificacionRepositorioCat notificacionRepositorio;

    @Autowired
    private final NotificacionParametroRepositorioCat notificacionParametroRepositorio;

    /**
     * Metodo para crear una notificacion.
     * 
     * @param idFormato
     * @param idServicio
     * @param titulo
     * @param contenido
     * @param direccionEnvio
     * @param anular
     * @param fechaEnvio
     * @param usuario
     * @param usuarioPrograma
     * @param mapaParametros
     * @param mapaAdjuntos
     */
    public void createNotificacion(long idFormato, Long idServicio, String titulo, String contenido, String direccionEnvio, String anular, Date fechaEnvio, String usuario, String usuarioPrograma, Map<String, String> mapaParametros, Map<String, String> mapaAdjuntos) {
        NotificacionCat notificacion = new NotificacionCat();

        titulo = StringUtils.defaultString(titulo, "<NO APLICA>");
        contenido = StringUtils.defaultString(contenido, "<NO APLICA>");
        direccionEnvio = StringUtils.defaultString(direccionEnvio, "<NO APLICA>");
        anular = StringUtils.defaultString(anular, "<NO APLICA>");

        notificacion.setIdNotificacion((long) 0);
        notificacion.setIdFormato(idFormato);
        notificacion.setIdServicio(idServicio);
        notificacion.setTitulo(StringUtils.truncate(titulo, 256));
        notificacion.setContenido(StringUtils.truncate(contenido, 4096));
        notificacion.setDireccionEnvio(StringUtils.truncate(direccionEnvio, 256));
        notificacion.setEstado( ConstantesCA00100.NOTIFICACION_ESTADO_PENDIENTE);
        notificacion.setAnular(StringUtils.truncate(anular, 8));
        notificacion.setFechaEnvio(fechaEnvio);
        notificacion.setUsuarioFecha(new Date());
        notificacion.setUsuario(StringUtils.truncate(usuario, 128));
        notificacion.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 128));
        notificacion = notificacionRepositorio.save(notificacion);

        if(mapaParametros != null) {
            for (Map.Entry<String, String> entry : mapaParametros.entrySet()) {
                createNotificacionParametro( 
                    notificacion.getIdNotificacion(), 
                    entry.getKey(), 
                    entry.getValue(), 
                    ConstantesCA00100.NOTIFICACION_PARAMETRO_PARAMETRO);
            }
        }

        if(mapaAdjuntos != null) {
            for (Map.Entry<String, String> entry : mapaAdjuntos.entrySet()) {
                createNotificacionParametro( 
                    notificacion.getIdNotificacion(), 
                    entry.getKey(), 
                    entry.getValue(), 
                    ConstantesCA00100.NOTIFICACION_PARAMETRO_ADJUNTO);
            }
        }
    }

    /**
     * Metodo para crear una notificacion parametro.
     * 
     * @param idNotificacion
     * @param llave
     * @param valor
     * @param tipo
     */
    private void createNotificacionParametro(Long idNotificacion, String llave, String valor, String tipo) {
        llave = StringUtils.defaultString(llave, "<NO APLICA>");
        valor = StringUtils.defaultString(valor, "<NO APLICA>");
        tipo = StringUtils.defaultString(tipo, "<NO APLICA>");
        
        NotificacionParametroCat notificacionParametro = new NotificacionParametroCat();
        notificacionParametro.setIdNotificacion(idNotificacion);
        notificacionParametro.setLlave(StringUtils.truncate(llave, 64));
        notificacionParametro.setValor(StringUtils.truncate(valor,512));
        notificacionParametro.setTipo(StringUtils.truncate(tipo, 8));
        notificacionParametroRepositorio.save(notificacionParametro);
    }
}
