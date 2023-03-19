package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_menu, nick, indice_menu, nombre_menu, task_flow, task_flow_informacion, tipo, indice_modulo, nombre_modulo, contexto, orden FROM GS_001_00.v_menu;"
 * 
 * Con codigo autonumerico en la columna id_menu y nick.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "v_menu", schema = "GS_001_00")
@IdClass (VMenuPK.class)
@Getter
@Setter
@ToString
public class VMenu implements Serializable{
    @Id
    @Column (name = "id_menu")
    private Long idMenu;
    
    @Id
    @Column (name = "nick")
    private String nick;
    
    @Column (name = "indice_menu", length = 64)
    private String indiceMenu;
    
    @Column (name = "nombre_menu", length = 256)
    private String nombreMenu;
    
    @Column (name = "task_flow", length = 256)
    private String taskFlow;
    
    @Column (name = "task_flow_informacion", length = 256)
    private String taskFlowInformacion;
    
    @Column (name = "tipo", length = 8)
    private String tipo;
    
    @Column (name = "indice_modulo", length = 64)
    private String indiceModulo;    
    
    @Column (name = "nombre_modulo", length = 256)
    private String nombreModulo;
    
    @Column (name = "contexto", length = 256)
    private String contexto;
    
    @Column (name = "orden")
    private Long orden;
}
