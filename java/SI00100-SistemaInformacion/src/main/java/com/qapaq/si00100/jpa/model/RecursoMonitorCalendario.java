package com.qapaq.si00100.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto para crear la entidad JPA en base a la consulta de la tabla de la consulta "SELECT id_recurso_monitor_calendario, id_recurso_monitor, fecha_presentacion, usuario, usuario_fecha, usuario_programa FROM SI_001_00.recurso_monitor_calendario;"
 * 
 * El indice es id_recurso_monitor_calendario autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "recurso_monitor_calendario", schema = "SI_001_00")
@Getter
@Setter
@JsonIgnoreProperties({ "usuario_programa" })
public class RecursoMonitorCalendario implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso_monitor_calendario")
    private Long idRecursoMonitorCalendario;

    @Column(name = "id_recurso_monitor")
    private Long idRecursoMonitor;

    @Column(name = "fecha_presentacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPresentacion;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
    
}
