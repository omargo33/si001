package com.qapaq.ga00100.jpa.model;

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

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Entidad JPA creada desde la consulta: "SELECT id_notificacion, id_formato, id_servicio, titulo, contenido, direccion_envio, estado, anular, fecha_envio, usuario, usuario_fecha, usuario_programa FROM GA_001_00.notificacion;"
 * 
 * Con codigo autonumerico en la columna id_notificacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-09
 * 
 */
@Entity
@Table(name = "notificacion", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @Column(name = "id_formato")
    private Long idFormato;

    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "titulo", length = 256)
    private String titulo;

    @Column(name = "contenido", length = 4096)
    private String contenido;

    @Column(name = "direccion_envio", length = 256)
    private String direccionEnvio;

    @Column(name = "estado", length = 8)
    private String estado;

    @Column(name = "anular", length = 8)
    private String anular;

    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;      

    
    @OneToMany(targetEntity = Formato.class)
    @JoinColumn(name = "id_formato", referencedColumnName = "id_formato")    
    private List<Formato> formatoList;    

}
