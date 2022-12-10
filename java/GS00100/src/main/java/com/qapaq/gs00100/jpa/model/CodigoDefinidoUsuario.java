package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Clase JPA para la tabla codigo_definido_usuario de la consulta "SELECT id_codigo_definido_usuario, id_modulo, grupo, codigo_texto, codigo_numero, nombre, descripcion, orden, estado, usuario, usuario_fecha, usuario_programa FROM GS_001_00.codigo_definido_usuario;";
 * 
 * Con codigo autonumerico en la columna id_codigo_definido_usuario.
 * 
 */
@Entity
@Table(name = "codigo_definido_usuario", schema = "GS_001_00") 
public class CodigoDefinidoUsuario implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_definido_usuario")
    private Long idCodigoDefinidoUsuario;
    @Column(name = "id_modulo")
    private Long idModulo;
    @Column(name = "grupo", length = 8)
    private String grupo;
    @Column(name = "codigo_texto", length = 8)
    private String codigoTexto;
    @Column(name = "codigo_numero")
    private Long codigoNumero;
    @Column(name = "nombre", length = 64)
    private String nombre;
    @Column(name = "descripcion", length = 512)
    private String descripcion;
    @Column(name = "orden")
    private Long orden;
    @Column(name = "estado", length = 8)
    private String estado;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    private String usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    /**
     * Metodo para crear la clase CodigoDefinidoUsuario.
     * 
     */
    public CodigoDefinidoUsuario() {
        super();
    }    
    
    /*
     * Metodos de acceso a los atributos de la clase
     */

    public Long getIdCodigoDefinidoUsuario() {
        return idCodigoDefinidoUsuario;
    }

    public void setIdCodigoDefinidoUsuario(Long idCodigoDefinidoUsuario) {
        this.idCodigoDefinidoUsuario = idCodigoDefinidoUsuario;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCodigoTexto() {
        return codigoTexto;
    }

    public void setCodigoTexto(String codigoTexto) {
        this.codigoTexto = codigoTexto;
    }

    public Long getCodigoNumero() {
        return codigoNumero;
    }

    public void setCodigoNumero(Long codigoNumero) {
        this.codigoNumero = codigoNumero;
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

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
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

    public String getUsuarioFecha() {
        return usuarioFecha;
    }

    public void setUsuarioFecha(String usuarioFecha) {
        this.usuarioFecha = usuarioFecha;
    }

    public String getUsuarioPrograma() {
        return usuarioPrograma;
    }

    public void setUsuarioPrograma(String usuarioPrograma) {
        this.usuarioPrograma = usuarioPrograma;
    }

    /**
     * Metodo para sobrecargar toString() y mostrar los valores de los atributos de
     * la clase.
     */
    @Override
    public String toString() {
        return "CodigoDefinidoUsuario [idCodigoDefinidoUsuario=" + idCodigoDefinidoUsuario + ", idModulo=" + idModulo + ", grupo=" + grupo + ", codigoTexto=" + codigoTexto + ", codigoNumero=" + codigoNumero + ", nombre=" + nombre + ", descripcion=" + descripcion + ", orden=" + orden + ", estado=" + estado + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}
