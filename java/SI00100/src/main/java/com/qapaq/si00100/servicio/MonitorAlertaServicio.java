package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.MonitorAlerta;
import com.qapaq.si00100.jpa.queries.MonitorAlertaRepositorio;

/**
 * Clase de servicio CRUD para Monitor Alerta Repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 */
@Service
@Transactional
public class MonitorAlertaServicio {
    
        private final MonitorAlertaRepositorio monitorAlertaRepositorio;
    
        /**
        * Constructor de la clase.
        * 
        * @param monitorAlertaRepositorio
        */
        public MonitorAlertaServicio(MonitorAlertaRepositorio monitorAlertaRepositorio) {
            this.monitorAlertaRepositorio = monitorAlertaRepositorio;
        }
    
        /**
        * Metodo guardar monitor alerta.
        * 
        * @param monitorAlerta
        * @return
        */
        public MonitorAlerta saveMonitorAlerta(MonitorAlerta monitorAlerta, String usuario, String usuarioPrograma) {
            
            monitorAlerta.setUsuario(usuario);
            monitorAlerta.setUsuarioFecha(new Date());
            monitorAlerta.setUsuarioPrograma(usuarioPrograma);
            return monitorAlertaRepositorio.save(monitorAlerta);
        }
    
        /**
        * Metodo listar todos los monitores alerta de manera paginada
        */
        public List<MonitorAlerta> findAllMonitorAlerta(Pageable pageable) {
            return monitorAlertaRepositorio.findAllPage(pageable);
        }
    
        /**
        * Metodo para buscar un monitor alerta por id_monitor_alerta.
        */
        public MonitorAlerta findMonitorAlertaById(Long id) {
            return monitorAlertaRepositorio.findByIdMonitorAlerta(id);
        }
    
        /**
        * Metodo para buscar un monitor alerta por nombre.
        */
        public List<MonitorAlerta> findMonitorAlertaByNombre(String nombre, Pageable pageable) {
            return monitorAlertaRepositorio.findAllByNombre(nombre, pageable);
        }
    
        /**
        * Metodo para borrar por id_monitor_alerta.
        */
        public void deleteMonitorAlertaById(Long id) {
            monitorAlertaRepositorio.deleteById(id);
        }
}
