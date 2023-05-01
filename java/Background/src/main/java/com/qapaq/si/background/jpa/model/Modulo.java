package com.qapaq.si.background.jpa.model;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entidad creada para la consulta "SELECT id_modulo, indice, nombre, contexto,
 * usuario, usuario_fecha, usuario_programa, estado FROM GS_001_00.modulo"
 * 
 * 
 * @author o.velez@qapaq.io
 * @date 2022/08/16
 * 
 */
@Entity
@Table(name = "modulo", schema = "GS_001_00")
@JsonIgnoreProperties({"idModulo", "usuarioFecha", "usuarioPrograma", "estado"})
public class Modulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_modulo")    
    private Long idModulo;
    @Column(name = "indice")
    private Long indice;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "contexto", length = 128)
    private String contexto;
    @Column(name = "usuario", length = 128)
    private String usuario;    
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
    @Column(name = "estado", length = 1)
    private String estado;

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public Long getIndice() {
        return indice;
    }

    public void setIndice(Long indice) {
        this.indice = indice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Metodo override toString.
     */
    @Override
    public String toString() {
        return "Modulo [idModulo=" + idModulo + ", indice=" + indice + ", nombre=" + nombre + ", contexto=" + contexto
                + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma
                + ", estado=" + estado + "]";
    }
}
