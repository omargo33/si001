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

    /**
     * Metodo para crear la clase Rol.
     * 
     */
    public Rol() {
        super();
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    @Override
    public String toString() {
        return "Rol [idRol=" + idRol + ", idModulo=" + idModulo + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}