package com.qapaq.ca00100.jpa.model;

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
 * Entidad JPA creada desde la consulta: "select id_permiso_rol,  url, rol, crear, actualizar, borrar, ver_auditoria where v_permiso_rol"
 * 
 * Con codigo autonumerico en la columna id_permiso_rol.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-11
 * 
 */
@Entity
@Table(name = "v_permiso_rol", schema = "GS_001_00", catalog = "GS_001_00" )
@Getter
@Setter
@ToString
public class VPermisoRol implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso_rol")
    private Long idPermisoRol;
    
    @Column(name = "url", length = 256)
    private String url;
    
    @Column(name = "rol", length = 256)
    private String rol;
    
    @Column(name = "crear")
    private Long crear;
    
    @Column(name = "actualizar")
    private Long actualizar;
    
    @Column(name = "borrar")
    private Long borrar;
    
    @Column(name = "ver_auditoria")
    private Long verAuditoria;    
}