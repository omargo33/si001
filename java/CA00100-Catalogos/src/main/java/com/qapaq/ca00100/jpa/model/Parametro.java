package com.qapaq.ca00100.jpa.model;

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
 * Clase JPA para la tabla modulo de la consulta: "SELECT id_parametro,
 * id_modulo, indice, clave, nombre, descripcion, valor_texto_01,
 * valor_texto_02, valor_numero_01, valor_numero_02, default_texto_01,
 * default_texto_02, default_numero_01, default_numero_02, usuario,
 * usuario_fecha, usuario_programa FROM GS_001_00.parametro";
 * 
 * Con codigo autonumerico en la columna id_parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-11-02
 * 
 */
@Entity
@Table(name = "parametro", schema = "GS_001_00", catalog = "GS_001_00")
@Getter
@Setter
@ToString
public class Parametro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private Long idParametro;

    @Column(name = "id_modulo")
    private Long idModulo;    

    @Column(name = "indice", length = 32)
    private String indice;

    @Column(name = "clave", length = 64)
    private String clave;

    @Column(name = "nombre", length = 64)
    private String nombre;

    @Column(name = "descripcion", length = 512)
    private String descripcion;

    @Column(name = "valor_texto_01", length = 64)
    private String valorTexto01;

    @Column(name = "valor_texto_02", length = 64)
    private String valorTexto02;

    @Column(name = "valor_numero_01")
    private Long valorNumero01;

    @Column(name = "valor_numero_02")
    private Long valorNumero02;

    @Column(name = "default_texto_01", length = 64)
    private String defaultTexto01;

    @Column(name = "default_texto_02", length = 64)
    private String defaultTexto02;

    @Column(name = "default_numero_01")
    private Long defaultNumero01;

    @Column(name = "default_numero_02")
    private Long defaultNumero02;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    private String usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;    
    
}
