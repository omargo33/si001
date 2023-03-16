package com.qapaq.catalogos.jpa.model;

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
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase JPA para la tabla modulo de la consulta "SELECT id_modulo, indice,
 * nombre, contexto, usuario, usuario_fecha, usuario_programa, estado FROM
 * GS_001_00.modulo";
 * 
 * Con codigo autonumerico en la columna id_modulo.
 * 
 */
@Entity
@Table(name = "modulo", schema = "GS_001_00", catalog = "GS_001_00")
@Getter
@Setter
@ToString
public class Modulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")    
    private Long idModulo;
    
    @Transient
    private String idxModulo;

    @Column(name = "indice", length = 32)
    private String indice;
    
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
    
    @Column(name = "estado", length = 8)
    private String estado;
}