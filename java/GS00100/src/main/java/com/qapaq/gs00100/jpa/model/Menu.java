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
 * Clase JPA para la tabla menu de la consulta: "SELECT id_menu, id_modulo, tipo, indice, nombre, task_flow, estado, orden, usuario, usuario_fecha, usuario_programa FROM GS_001_00.menu"
 * 
 * Con codigo autonumerico en la columna id_menu.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "menu", schema = "GS_001_00")
@Getter
@Setter
@ToString
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Long idMenu;
    
    @Column(name = "id_modulo")
    private Long idModulo;
    
    @Column(name = "tipo", length = 8)
    private String tipo;
    
    @Column(name = "indice", length = 8)
    private String indice;
    
    @Column(name = "nombre", length = 64)
    private String nombre;
    
    @Column(name = "task_flow", length = 256)
    private String taskFlow;
    
    @Column(name = "estado", length = 8)
    private String estado;
    
    @Column(name = "orden")
    private Long orden;
    
    @Column(name = "usuario", length = 128)
    private String usuario;
    
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @OneToMany(targetEntity = Permiso.class)
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")    
    private List<Permiso> permisoList;    
}
