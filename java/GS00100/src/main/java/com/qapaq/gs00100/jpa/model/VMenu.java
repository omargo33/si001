package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

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

    /**
     * Constructor por defecto.
     */
    public VMenu() {
        super();
    }
    
    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIndiceMenu() {
        return indiceMenu;
    }

    public void setIndiceMenu(String indiceMenu) {
        this.indiceMenu = indiceMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public String getTaskFlow() {
        return taskFlow;
    }

    public void setTaskFlow(String taskFlow) {
        this.taskFlow = taskFlow;
    }

    public String getTaskFlowInformacion() {
        return taskFlowInformacion;
    }

    public void setTaskFlowInformacion(String taskFlowInformacion) {
        this.taskFlowInformacion = taskFlowInformacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIndiceModulo() {
        return indiceModulo;
    }

    public void setIndiceModulo(String indiceModulo) {
        this.indiceModulo = indiceModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "VMenu [idMenu=" + idMenu + ", nick=" + nick + ", indiceMenu=" + indiceMenu + ", nombreMenu=" + nombreMenu + ", taskFlow=" + taskFlow + ", taskFlowInformacion=" + taskFlowInformacion + ", tipo=" + tipo + ", indiceModulo=" + indiceModulo + ", nombreModulo=" + nombreModulo + ", contexto=" + contexto + ", orden=" + orden + "]";
    }
}
