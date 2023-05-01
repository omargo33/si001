package com.qapaq.si.background.jpa.model;

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
 * Entidad creada por la consulta "SELECT id_ciudad, nombre, latitud, longitud,
 * usuario, usuario_fecha, usuario_programa FROM SI_001_00.ciudad;
 * 
 * @author o.velez@qapaq.io
 * @date 2022/08/14
 * 
 */
@Entity
@Table(name = "ciudad", schema = "SI_001_00")
public class Ciudad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ciudad")
    private Long idCiudad;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "latitud", length = 64)
    private String latitud;
    @Column(name = "longitud", length = 64)
    private String longitud;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;


    /** generar todos los getters y setters */
    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
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
     * toString para todos los campos de la entidad
     */
    @Override
    public String toString() {
        return "Ciudad{" + "idCiudad=" + idCiudad + ", nombre=" + nombre + ", latitud=" + latitud + ", longitud="
                + longitud + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma="
                + usuarioPrograma + '}';
    }

}
