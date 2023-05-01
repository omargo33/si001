package com.qapaq.ar00100.jpa.model;

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
 * Entidad JPA creada desde la consulta: "SELECT id_auditoria, nombre, valor_01,
 * valor_02, usuario, usuario_fecha, usuario_programa, elemento FROM
 * AR_001_00.auditoria;"
 * 
 * Con codigo autonumerico en la columna id_auditoria.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
@Entity
@Table(name = "auditoria", schema = "AR_001_00")
@Getter
@Setter
@ToString
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @Column(name = "nombre", length = 128)
    private String nombre;

    @Column(name = "valor_01", length = 256)
    private String valor01;

    @Column(name = "valor_02", length = 256)
    private String valor02;

    @Column(name = "elemento", length = 64)
    private String elemento;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}
