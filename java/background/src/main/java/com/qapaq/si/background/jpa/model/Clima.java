package com.qapaq.si.background.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Clase que representa un clima desde la consulta = "SELECT id_clima,
 * id_ciudad, json, usuario, usuario_fecha, usuario_programa FROM
 * SI_001_00.clima;"
 * 
 * @author o.velez@qapaq.io
 * @date 2022-08-14
 * 
 */
@Entity
@Table(name = "clima", schema = "SI_001_00")
public class Clima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_clima")
    private Long idClima;
    @Column(name = "id_ciudad")
    private Long idCiudad;
    @Column(name = "json", length = 1536)
    private String json;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", insertable = false, updatable = false)    
    private Ciudad ciudad;    

    /**
     * Metodo para crear el objeto.
     * 
     */
    public static Clima create() {
        return new Clima();
    }

    /** generar todos los getters y setters */
    public Long getIdClima() {
        return idClima;
    }

    public void setIdClima(Long idClima) {
        this.idClima = idClima;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
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

    /**
     * Metodo sobrecargar toString.
     */
    @Override
    public String toString() {
        return "Clima [idClima=" + idClima + ", idCiudad=" + idCiudad + ", json=" + json + ", usuario=" + usuario
                + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + "]";
    }    
}
