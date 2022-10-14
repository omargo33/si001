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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad JPA en base a la consulta de la tabla "SELECT id_monitor_alerta, id_monitor, nombre, descripcion, usuario, usuario_fecha, usuario_programa FROM SI_001_00.monitor_alerta;"
 * 
 * El indice es id_monitor_alerta autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "monitor_alerta", schema = "SI_001_00")
@Getter
@Setter
@ToString
@JsonIgnoreProperties ({ "usuario_programa" })
public class MonitorAlerta implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_monitor_alerta")
    private Long idMonitorAlerta;

    @NotNull(message = "E-SI00100-2")
    @Column(name = "id_monitor")
    private Long idMonitor;

    @NotBlank(message = "E-SI00100-12")
    @Column(name = "nombre", length = 128)
    private String nombre;

    @NotBlank(message = "E-SI00100-12")
    @Column(name = "descripcion", length = 2048)
    private String descripcion;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;    
}