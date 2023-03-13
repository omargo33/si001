package com.qapaq.seguridades.jpa.model;

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
@Getter
@Setter
@ToString
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
    private String tokenPassword;
    
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
}
