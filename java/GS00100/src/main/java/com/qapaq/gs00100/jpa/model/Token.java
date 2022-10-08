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
 * Entidad JPA creada desde la consulta: "SELECT id_token, id_usuario, tipo,
 * social_nick, correo, token, validador, estado, usuario, usuario_fecha,
 * usuario_programa FROM GS_001_00.token;"
 * 
 * Con codigo autonumerico en la columna id_token.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "token", schema = "GS_001_00")
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long idToken;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "tipo", length = 8)
    private String tipo;
    @Column(name = "social_nick", length = 256)
    private String socialNick;
    @Column(name = "correo", length = 256)
    private String correo;
    @Column(name = "token", length = 512)
    private String token;
    @Column(name = "validador", length = 512)
    private String validador;
    @Column(name = "estado", length = 8)
    private String estado;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @JoinColumn(name = "id_token", referencedColumnName = "id_token", insertable = false, updatable = false)
    @OneToMany
    private List<Token> tokenList;

    /**
     * Metodo constructor por defecto.
     */
    public Token() {
        super();
    }

    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSocialNick() {
        return socialNick;
    }

    public void setSocialNick(String socialNick) {
        this.socialNick = socialNick;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
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

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    /**
     * Metodo sobrecargar toString.
     */
    @Override
    public String toString() {
        return "Token [idToken=" + idToken + ", idUsuario=" + idUsuario + ", tipo=" + tipo + ", socialNick=" + socialNick
                + ", correo=" + correo + ", token=" + token + ", validador=" + validador + ", estado=" + estado
                + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma
                + ", tokenList=" + tokenList + "]";
    }
}
