package com.qapaq.seguridades.jpa.model;

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
 * Entidad JPA creada desde la consulta: "SELECT id_direccion, elemento, direccion, navegador_dispositivo, usuario, usuario_fecha, usuario_programa FROM AR_001_00.direccion;"
 * 
 * Con codigo autonumerico en la columna id_direccion.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-11-25
 * 
 */
@Entity
@Table(name = "direccion",  schema = "AR_001_00", catalog = "AR_001_00")
@Getter
@Setter
@ToString
public class Direccion  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long idDireccion;

    @Column(name = "elemento", length = 64)
    private String elemento;

    @Column(name = "direccion", length = 128)
    private String direccionDispositivo;

    @Column(name = "navegador_dispositivo", length = 1024)
    private String navegadorDispositivo;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}
