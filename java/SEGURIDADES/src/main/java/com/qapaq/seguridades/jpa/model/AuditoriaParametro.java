package com.qapaq.seguridades.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_auditoria_parametro, id_auditoria, nombre, direccion, valor FROM AR_001_00.auditoria_parametro;"
 * 
 * Con codigo autonumerico en la columna id_auditoria_parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
@Entity
@Table(name = "auditoria_parametro", schema = "AR_001_00")
@Getter
@Setter
@ToString
public class AuditoriaParametro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria_parametro")
    private Long idAuditoriaParametro;

    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @Column(name = "nombre", length = 128)
    private String nombre;

    @Column(name = "direccion", length = 128)
    private String direccion;

    @Column(name = "valor", length = 256)
    private String valor;
}