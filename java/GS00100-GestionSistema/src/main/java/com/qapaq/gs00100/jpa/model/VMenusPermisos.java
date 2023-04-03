package com.qapaq.gs00100.jpa.model;

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
 * Clase JPA para la tabla v_menus_permisos de la consulta "select id_menus_permisos, nick, indice_modulo, nombre_modulo, contexto, id_menu, tipo, indice_menu, nombre_menu, task_flow, task_flow_informacion, orden, crear, actualizar, borrar, ver_auditoria from v_menus_permisos";
 * 
 * Con codigo autonumerico en la columna id_menus_permisos.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-27
 * 
 */
@Entity
@Table(name = "v_menus_permisos", schema = "GS_001_00", catalog = "GS_001_00")
@Getter
@Setter
@ToString
public class VMenusPermisos implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menus_permisos")
    private Long idMenusPermisos;
    
    @Column(name = "nick", length = 128)
    private String nick;
    
    @Column(name = "indice_modulo", length = 32)
    private String indiceModulo;
    
    @Column(name = "nombre_modulo", length = 128)
    private String nombreModulo;
    
    @Column(name = "contexto", length = 128)
    private String contexto;
    
    @Column(name = "id_menu")
    private Long idMenu;
    
    @Column(name = "tipo", length = 8)
    private String tipo;
    
    @Column(name = "indice_menu", length = 32)
    private String indiceMenu;
    
    @Column(name = "nombre_menu", length = 128)
    private String nombreMenu;
    
    @Column(name = "task_flow", length = 64)
    private String taskFlow;
    
    @Column(name = "task_flow_informacion", length = 64)
    private String taskFlowInformacion;
    
    @Column(name = "orden")
    private Long orden;
    
    @Column(name = "crear", length = 8)
    private String crear;
    
    @Column(name = "actualizar", length = 8)
    private String actualizar;
    
    @Column(name = "borrar", length = 8)
    private String borrar;
    
    @Column(name = "ver_auditoria", length = 8)
    private String verAuditoria;
} 
