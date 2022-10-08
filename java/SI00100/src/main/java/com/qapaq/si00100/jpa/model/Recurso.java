package com.qapaq.si00100.jpa.model;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad JPA en base a la consulta de la tabla "SELECT id_recurso, id_proyecto, nombre, tipo, indice, duracion, tipo_orientacion, sonido, brillo, relacion, definicion, qr, qr_posicion_x, qr_posicion_y, estado, tipo_documento, usuario, usuario_fecha, usuario_programa FROM SI_001_00.recurso;"
 * 
 * El indice es id_recurso autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "recurso", schema = "SI_001_00")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "estado", "usuario_programa" })
public class Recurso implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Long idRecurso;

    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "nombre", length = 128)
    private String nombre;

    @Column(name = "tipo", length = 8)
    private String tipo;

    @Column(name = "indice", length = 64)
    private String indice;

    @Column(name = "duracion")
    private Double duracion;

    @Column(name = "tipo_orientacion", length = 8)
    private String tipoOrientacion;

    @Column(name = "sonido")
    private Long sonido;

    @Column(name = "brillo")
    private Long brillo;

    @Column(name = "relacion", length = 8)
    private String relacion;

    @Column(name = "definicion", length = 8)
    private String definicion;

    @Column(name = "qr", length = 8)
    private String qr;

    @Column(name = "qr_posicion_x")
    private Long qrPosicionX;

    @Column(name = "qr_posicion_y")
    private Long qrPosicionY;

    @Column(name = "estado", length = 8)
    private String estado;

    @Column(name = "tipo_documento", length = 8)
    private String tipoDocumento;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}