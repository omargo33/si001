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
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    /**
     * Metodo para crear la clase Menu.
     * 
     */
    public Menu() {
        super();
    }

    /*
     * Metodos de acceso a los atributos de la clase
     */

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTaskFlow() {
        return taskFlow;
    }

    public void setTaskFlow(String taskFlow) {
        this.taskFlow = taskFlow;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getOrden() {
        return orden;
    }
    
    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getUsuarioFecha() {
        return usuarioFecha;
    }

    public void setUsuarioFecha(Date usuarioFecha) {
        this.usuarioFecha = usuarioFecha;
    }

    public String getUsuarioPrograma() {
        return usuarioPrograma;
    }

    public void setUsuarioPrograma(String usuarioPrograma) {
        this.usuarioPrograma = usuarioPrograma;
    }
    /**
     * Metodo para sobrecargar el metodo toString de la clase Object.
     */
    @Override
    public String toString(){
        return "Menu [idMenu=" + idMenu + ", idModulo=" + idModulo + ", tipo=" + tipo + ", indice=" + indice + ", nombre=" + nombre + ", taskFlow=" + taskFlow + ", estado=" + estado + ", orden=" + orden + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}
