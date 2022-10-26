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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad jpa en base a la consulta de la tabla "SELECT
 * id_clima, id_ciudad, json, usuario, usuario_fecha, usuario_programa FROM
 * SI_001_00.clima;"
 * 
 * El indice es id_ciudad autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "clima", schema = "SI_001_00")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "usuario_programa" })
public class Clima implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clima")
    private Long idClima;

    @Column(name = "id_ciudad")
    private Long idCiudad;

    @NotBlank(message = "E-SI00100-12")
    @Column(name = "json", length = 1536)
    private String json;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}