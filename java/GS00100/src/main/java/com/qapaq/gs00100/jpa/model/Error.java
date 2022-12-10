package com.qapaq.gs00100.jpa.model;

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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_error, indice, mensaje, descripcion, usuario, usuario_fecha, usuario_programa FROM GS_001_00.error;"
 * 
 * Con codigo autonumerico en la columna id_error.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "error", schema = "GS_001_00")
@Getter
@Setter
@ToString
public class Error implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_error")
    private Long idError;

    @Column(name = "indice")
    private Long indice;

    @Column(name = "mensaje", length = 256)
    private String mensaje;

    @Column(name = "descripcion", length = 256)
    private String descripcion;

    @Column(name = "usuario", length = 256)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}
