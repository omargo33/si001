package com.qapaq.si.background.jpa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Table;

/**
 * Entidad creada para la consulta "SELECT id_parametro, id_modulo, indice,
 * clave, nombre, descripcion, valor_texto_01, valor_texto_02, valor_numero_01,
 * valor_numero_02, default_texto_01, default_texto_02, default_numero_01,
 * default_numero_02, usuario, usuario_fecha, usuario_programa FROM
 * GS_001_00.parametro;
 * "
 * 
 * 
 * @author o.velez@qapaq.io
 * @date 2022/08/16
 * 
 */
@Entity
@Table(name = "parametro", schema = "GS_001_00")
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_parametro")
    private Long idParametro;
    @Column(name = "id_modulo")
    private Long idModulo;
    @Column(name = "indice")
    private Long indice;
    @Column(name = "clave", length = 8)
    private String clave;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "descripcion", length = 512)
    private String descripcion;
    @Column(name = "valor_texto_01", length = 256)
    private String valorTexto01;
    @Column(name = "valor_texto_02", length = 256)
    private String valorTexto02;
    @Column(name = "valor_numero_01")
    private Long valorNumero01;
    @Column(name = "valor_numero_02")
    private Long valorNumero02;
    @Column(name = "default_texto_01", length = 256)
    private String defaultTexto01;
    @Column(name = "default_texto_02", length = 256)
    private String defaultTexto02;
    @Column(name = "default_numero_01")
    private Long defaultNumero01;
    @Column(name = "default_numero_02")
    private Long defaultNumero02;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @ManyToOne
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo", insertable = false, updatable = false)
    private Modulo modulo;        

    // Getters and Setters
    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValorTexto01() {
        return valorTexto01;
    }

    public void setValorTexto01(String valorTexto01) {
        this.valorTexto01 = valorTexto01;
    }

    public String getValorTexto02() {
        return valorTexto02;
    }

    public void setValorTexto02(String valorTexto02) {
        this.valorTexto02 = valorTexto02;
    }

    public Long getValorNumero01() {
        return valorNumero01;
    }

    public void setValorNumero01(Long valorNumero01) {
        this.valorNumero01 = valorNumero01;
    }

    public Long getValorNumero02() {
        return valorNumero02;
    }

    public void setValorNumero02(Long valorNumero02) {
        this.valorNumero02 = valorNumero02;
    }

    public String getDefaultTexto01() {
        return defaultTexto01;
    }

    public void setDefaultTexto01(String defaultTexto01) {
        this.defaultTexto01 = defaultTexto01;
    }

    public String getDefaultTexto02() {
        return defaultTexto02;
    }

    public void setDefaultTexto02(String defaultTexto02) {
        this.defaultTexto02 = defaultTexto02;
    }

    public Long getDefaultNumero01() {
        return defaultNumero01;
    }

    public void setDefaultNumero01(Long defaultNumero01) {
        this.defaultNumero01 = defaultNumero01;
    }

    public Long getDefaultNumero02() {
        return defaultNumero02;
    }

    public void setDefaultNumero02(Long defaultNumero02) {
        this.defaultNumero02 = defaultNumero02;
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
     * Metodo override toString.
     */
    @Override
    public String toString() {
        return "Parametro [idParametro=" + idParametro + ", idModulo=" + idModulo + ", indice=" + indice + ", clave="
                + clave + ", nombre=" + nombre + ", descripcion=" + descripcion + ", valorTexto01=" + valorTexto01
                + ", valorTexto02=" + valorTexto02 + ", valorNumero01=" + valorNumero01 + ", valorNumero02="
                + valorNumero02 + ", defaultTexto01=" + defaultTexto01 + ", defaultTexto02=" + defaultTexto02
                + ", defaultNumero01=" + defaultNumero01 + ", defaultNumero02=" + defaultNumero02 + ", usuario="
                + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}
