package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_rol_usuario, id_rol, id_usuario, usuario, usuario_fecha, usuario_programa FROM GS_001_00.rol_usuario;"
 * 
 * Con codigo autonumerico en la columna id_rol_usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "rol_usuario", schema = "GS_001_00")
@Getter
@Setter
@ToString
public class RolUsuario implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_usuario")
    private Long idRolUsuario;
    
    @Column(name = "id_rol")
    private Long idRol;
    
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    @OneToOne     
    private Rol rol;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @OneToOne
    private Usuario usuarioInfo;

}