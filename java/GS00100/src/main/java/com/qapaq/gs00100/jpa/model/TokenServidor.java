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
 * Entidad JPA creada desde la consulta: "SELECT id_token_servidor, id_token, tipo, servidor, password, usuario_fecha, usuario_programa FROM GS_001_00.token_servidor"
 * 
 * Con codigo autonumerico en la columna id_token_servidor.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "token_servidor", schema = "GS_001_00")
public class TokenServidor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token_servidor")
    private Long idTokenServidor;
    @Column(name = "id_token")
    private Long idToken;
    @Column(name = "tipo", length = 8)
    private String tipo;
    @Column(name = "servidor", length = 256)
    private String servidor;
    @Column(name = "password", length = 256)
    private String password;
    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    public Long getIdTokenServidor() {
        return idTokenServidor;
    }

    public void setIdTokenServidor(Long idTokenServidor) {
        this.idTokenServidor = idTokenServidor;
    }

    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "TokenServidor [idTokenServidor=" + idTokenServidor + ", idToken=" + idToken + ", tipo=" + tipo
                + ", servidor=" + servidor + ", password=" + password + ", usuarioFecha=" + usuarioFecha
                + ", usuarioPrograma=" + usuarioPrograma + "]";
    }
}
