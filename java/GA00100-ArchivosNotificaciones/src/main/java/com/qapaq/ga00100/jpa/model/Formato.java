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
 * Entidad JPA creada desde la consulta: "SELECT id_formato, indice, nombre, formato_html, usuario, usuario_fecha, usuario_programa FROM GA_001_00.formato"
 * 
 * Con codigo autonumerico en la columna id_formato.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-09
 * 
 */
@Entity
@Table(name = "formato", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class Formato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formato")
    private Long idFormato;

    @Column(name = "indice", length = 32)
    private String indice;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "nombre", length = 128)
    private String nombre;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "formato_html", length = 4096)
    private String formatoHtml;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
}
