package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_usuario, id_archivo, nick, nombre, apellido, usuario, validador, usuario_fecha, usuario_programa, estado, contador_ingreso, contador_fecha FROM GS_001_00.usuario;"
 * 
 * Con codigo autonumerico en la columna id_usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "usuario", schema = "GS_001_00")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_archivo")
    private Long idArchivo;
    @Column(name = "nick", length = 128)
    private String nick;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "apellido", length = 128)
    private String apellido;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "validador", length = 128)
    private String validador;
    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
    @Column(name = "estado", length = 128)
    private String estado;
    @Column(name = "contador_ingreso")
    private Integer contadorIngreso;
    @Column(name = "contador_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contadorFecha;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @OneToMany
    private List<Token> tokenList;

    /**
     * Metodo constructor por defecto.
     */
    public Usuario() {
        super();
    }

    /**
     * Metodos get y set.
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
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

    public Integer getContadorIngreso() {
        return contadorIngreso;
    }

    public void setContadorIngreso(Integer contadorIngreso) {
        this.contadorIngreso = contadorIngreso;
    }

    public Date getContadorFecha() {
        return contadorFecha;
    }

    public void setContadorFecha(Date contadorFecha) {
        this.contadorFecha = contadorFecha;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    /**
     * Metodo toString.
     */
    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", idArchivo=" + idArchivo + ", nick=" + nick + ", nombre=" + nombre
                + ", apellido=" + apellido + ", usuario=" + usuario + ", validador=" + validador + ", usuarioFecha="
                + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + ", estado=" + estado + ", contadorIngreso="
                + contadorIngreso + ", contadorFecha=" + contadorFecha + ", tokenList=" + tokenList + "]";
    }      
}
