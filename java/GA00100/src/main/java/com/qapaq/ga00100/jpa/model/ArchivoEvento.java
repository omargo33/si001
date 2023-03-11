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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_archivo_evento, id_archivo, informacion, tipo, usuario, usuario_fecha, usuario_programa FROM GA_001_00.archivo_evento"
 * 
 * Con codigo autonumerico en la columna id_archivo_evento.
 * 
 * @author o.velez@jardinazuayo.fin.ec
 * @date 2023-03-09
 * 
 */
@Entity
@Table(name = "archivo_evento", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class ArchivoEvento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo_evento")
    private Long idArchivoEvento;

    @Column(name = "id_archivo")
    private Long idArchivo;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "informacion", length = 512)
    private String informacion;

    @NotBlank(message = "E-GA00100-12")
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