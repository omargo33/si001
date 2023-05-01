package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_permiso, id_menu, id_rol, crear,
 * actualizar, borrar, ver_auditoria, usuario, usuario_fecha, usuario_programa
 * FROM GS_001_00.permiso"
 * 
 * Con codigo autonumerico en la columna id_permiso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "permiso")
@Getter
@Setter
@ToString
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long idPermiso;
    
    @Column(name = "id_menu")
    private Long idMenu;
    
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "crear", length = 8)
    private String crear;
    
    @Column(name = "actualizar", length = 8)
    private String actualizar;
    
    @Column(name = "borrar", length = 8)
    private String borrar;
    
    @Column(name = "ver_auditoria", length = 8)
    private String verAuditoria;
    
    @Column(name = "usuario", length = 128)
    private String usuario;
    
    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
    
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", insertable = false, updatable = false)
    @OneToMany
    private List<Menu> menuList;
    
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    @OneToMany
    private List<Rol> rolList;    
}
