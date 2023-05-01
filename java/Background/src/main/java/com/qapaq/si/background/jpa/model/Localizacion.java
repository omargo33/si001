package com.qapaq.si.background.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entidad creada por la consulta "SELECT id_localizacion, id_ciudad, nombre,
 * latitud, longitud, usuario, usuario_fecha, usuario_programa FROM
 * SI_001_00.localizacion;"
 * 
 * @author o.velez@qapaq.io
 * @date 2022/08/14
 * 
 */
@Entity
@Table(name = "localizacion", schema = "SI_001_00")
public class Localizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_localizacion")
    private Long idLocalizacion;
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
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @ManyToOne
    @JoinColumn (name = "id_ciudad", insertable = false, updatable = false)
    private Ciudad ciudad;
    

    /**
     * Metodo para crear el objeto.
     * 
     */
    public static Localizacion create() {
        return new Localizacion();
    }

    // generar todos los getters y setters
    public Long getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Long idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Localizacion [idLocalizacion=" + idLocalizacion + ", idCiudad=" + idCiudad + ", nombre=" + nombre
                + ", latitud=" + latitud + ", longitud=" + longitud + ", usuario=" + usuario + ", usuarioFecha="
                + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}
