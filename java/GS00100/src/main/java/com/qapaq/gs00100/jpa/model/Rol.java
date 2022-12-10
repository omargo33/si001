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
 * Entidad creada desde la consulta: SELECT id_rol, id_modulo, nombre, tipo, estado, usuario, usuario_fecha, usuario_programa FROM GS_001_00.rol
 * 
 * Con codigo autonumerico en la columna id_rol.
 * 
 * @author o.velez@qapac.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "rol", schema = "GS_001_00") 
@Getter
@Setter
@ToString
public class Rol implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_rol")
    private Long idRol;

    @Column (name = "id_modulo")
    private Long idModulo;

    @Column (name = "nombre", length = 64)
    private String nombre;

    @Column (name = "tipo", length = 8)
    private String tipo;

    @Column (name = "estado", length = 8)
    private String estado;

    @Column (name = "usuario", length = 128)
    private String usuario;

    @Column (name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column (name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}