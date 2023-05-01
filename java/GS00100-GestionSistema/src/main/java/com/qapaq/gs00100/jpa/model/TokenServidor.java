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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


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
@Table(name = "token_servidor")
@Getter
@Setter
@ToString
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
}
