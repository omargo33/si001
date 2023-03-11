package com.qapaq.ga00100.jpa.model;
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
import com.qapaq.ga00100.validadores.InformacionNombre;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad jpa en base a la consulta de la tabla "SELECT id_informacion, nombre, valor_01, valor_02, usuario, usuario_fecha, usuario_programa FROM SI_001_00.informacion"
 * 
 * El indice es id_informacion autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "informacion", schema = "GA_001_00")
@Getter
@Setter
@InformacionNombre(titulo = "Informacion", message = "E-GA00100-11")
@ToString
@JsonIgnoreProperties({ "usuario_programa" })
public class Informacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacion")
    private Long idInformacion;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "nombre", length = 128)
    private String nombre;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "valor_01", length = 256)
    private String valor01;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "valor_02", length = 256)
    private String valor02;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma; 
}