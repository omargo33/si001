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
 * Entidad JPA creada desde la consulta: "SELECT id_acceso, nombre, token, token_api, usuario_fecha, usuario_programa FROM AR_001_00.acceso;"
 * 
 * Con codigo autonumerico en la columna id_acceso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-11-25
 * 
 */
@Entity
@Table(name = "acceso", schema = "AR_001_00")
@Getter
@Setter
@ToString
public class Acceso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acceso")
    private Long idAcceso;

    @Column(name = "nombre", length = 256)
    private String nombre;

    @Column(name = "token", length = 256)
    private String token;

    @Column(name = "token_api", length = 2048)
    private String tokenApi;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;      
}
