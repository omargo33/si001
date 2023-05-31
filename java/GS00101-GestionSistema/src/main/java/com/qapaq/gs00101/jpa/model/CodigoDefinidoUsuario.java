package com.qapaq.gs00101.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase JPA para la tabla codigo_definido_usuario de la consulta "SELECT id_codigo_definido_usuario, id_modulo, grupo, codigo_texto, codigo_numero, nombre, descripcion, orden, estado, usuario, usuario_fecha, usuario_programa FROM GS_001_00.codigo_definido_usuario;";
 * 
 * Con codigo autonumerico en la columna id_codigo_definido_usuario.
 * 
 */
@Entity
@Table(name = "codigo_definido_usuario") 
@Getter
@Setter
@ToString
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
}
