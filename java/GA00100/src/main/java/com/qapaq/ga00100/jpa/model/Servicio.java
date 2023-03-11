package com.qapaq.ga00100.jpa.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_servicio, indice, nombre, valor_01, valor_02, tipo, usuario, usuario_fecha, usuario_programa FROM GA_001_00.servicio"
 * 
 * Con codigo autonumerico en la columna id_servicio.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-10
 * 
 */
@Entity
@Table(name = "servicio", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "indice", length = 32)
    private String indice;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "nombre", length = 128)
    private String nombre;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "valor_01", length = 512)
    private String valor01;

    @Column(name = "valor_02", length = 512)
    private String valor02;

    @Column(name = "tipo", length = 8)
    private String tipo;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}
