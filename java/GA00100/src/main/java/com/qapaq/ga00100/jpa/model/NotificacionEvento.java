package com.qapaq.ga00100.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Entidad JPA creada desde la consulta: "SELECT id_notificacion_evento, id_notificacion, informacion, tipo, usuario, usuario_fecha, usuario_programa FROM GA_001_00.notificacion_evento"
 * 
 * Con codigo autonumerico en la columna id_notificacion_evento.
 * 
 * @author o.velez@jardinazuayo.fin.ec
 * @date 2023-03-09
 * 
 */
@Entity
@Table(name = "notificacion_evento", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class NotificacionEvento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_evento")
    private Long idNotificacionEvento;

    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "informacion", length = 4096)
    private String informacion;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "tipo", length = 8)
    private String tipo;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 128)
    private String usuarioPrograma;

    @OneToMany(targetEntity = Notificacion.class)
    @JoinColumn(name = "id_notificacion", referencedColumnName = "id_notificacion")
    private List<Notificacion> notificacionList;    
}