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
 * Entidad JPA creada desde la consulta: "SELECT id_error, indice, mensaje, descripcion, usuario, usuario_fecha, usuario_programa FROM GS_001_00.error;"
 * 
 * Con codigo autonumerico en la columna id_error.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "error", schema = "GS_001_00")
public class Error implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_error")
    private Long idError;
    @Column(name = "indice")
    private Long indice;
    @Column(name = "mensaje", length = 256)
    private String mensaje;
    @Column(name = "descripcion", length = 256)
    private String descripcion;
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
    public Error() {
        super();
    }

    public Long getIdError() {
        return idError;
    }

    public void setIdError(Long idError) {
        this.idError = idError;
    }

    public Long getIndice() {
        return indice;
    }

    public void setIndice(Long indice) {
        this.indice = indice;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * Metodo toString() para la clase Error.
     */
    @Override
    public String toString() {
        return "Error [idError=" + idError + ", indice=" + indice + ", mensaje=" + mensaje + ", descripcion=" + descripcion + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}
