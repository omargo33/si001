package com.qapaq.si00100.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad JPA en base a la consulta de la tabla "SELECT id_recurso_monitor, id_recurso, id_monitor, usuario, usuario_fecha, usuario_programa FROM SI_001_00.recurso_monitor;"
 * 
 * El indice es id_recurso_monitor autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "recurso_monitor", schema = "SI_001_00")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "usuario_programa" })
public class RecursoMonitor implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso_monitor")
    private Long idRecursoMonitor;

    @NotBlank(message = "E-SI00100-12")
    @Column(name = "id_recurso")
    private Long idRecurso;

    @NotBlank(message = "E-SI00100-12")
    @Column(name = "id_monitor")
    private Long idMonitor;

    @NotBlank(message = "E-SI00100-12")
    @Column(name = "estado", length = 8)
    private String estado;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    /**
     * Relacion con RecursoMonitor-RecursoMonitorCalendario
     */
    @OneToMany(targetEntity = RecursoMonitorCalendario.class)
    @JoinColumn(name = "id_recurso_monitor", referencedColumnName = "id_recurso_monitor")
    private List<RecursoMonitorCalendario> recursoMonitorCalendario;

    /**
     * Relacion con RecursoMonitor-RecursoMonitorPresentacion
     */
    @OneToMany(targetEntity = RecursoMonitorPresentacion.class)
    @JoinColumn(name = "id_recurso_monitor", referencedColumnName = "id_recurso_monitor")
    private List<RecursoMonitorPresentacion> recursoMonitorPresentacion;

}
