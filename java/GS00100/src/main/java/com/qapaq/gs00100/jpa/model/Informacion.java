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
 * Entidad JPA creada desde la consulta: "SELECT id_informacion, nombre,
 * valor_01, valor_02, usuario, usuario_fecha, usuario_programa FROM
 * GS_001_00.informacion;"
 * 
 * Con codigo autonumerico en la columna id_informacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "informacion", schema = "GS_001_00")
public class Informacion implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacion")
    private Long idInformacion;
    @Column(name = "nombre", length = 256)
    private String nombre;
    @Column(name = "valor_01", length = 256)
    private String valor01;
    @Column(name = "valor_02", length = 256)
    private String valor02;
    @Column(name = "usuario", length = 256)
    private String usuario;
    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    /**
     * Constructor por defecto.
     */
    public Informacion() {
        super();
    }

    public Long getIdInformacion() {
        return idInformacion;
    }

    public void setIdInformacion(Long idInformacion) {
        this.idInformacion = idInformacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor01() {
        return valor01;
    }

    public void setValor01(String valor01) {
        this.valor01 = valor01;
    }

    public String getValor02() {
        return valor02;
    }

    public void setValor02(String valor02) {
        this.valor02 = valor02;
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
     * Metodo para sobreescribir el metodo toString() de la clase Object.
     */
    @Override
    public String toString() {
        return "Informacion [idInformacion=" + idInformacion + ", nombre=" + nombre + ", valor01=" + valor01
                + ", valor02=" + valor02 + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha
                + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}
