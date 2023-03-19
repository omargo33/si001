package com.qapaq.ga00100.jpa.model;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_grupo, id, esquema, tabla, largo_maximo, extensiones, ancho, alto, maximo_archivo, usuario, usuario_fecha, usuario_programa FROM GA_001_00.grupo"
 * 
 * Con codigo autonumerico en la columna id_grupo.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-09
 * 
 */
@Entity
@Table(name = "grupo", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo", insertable = false, updatable = false)
    private Long idGrupo;

    @Column(name = "id")
    private Long id;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "esquema", length = 16)
    private String esquema;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "tabla", length = 32)
    private String tabla;

    @Column(name = "largo_maximo")
    private Integer largoMaximo;

    @Column(name = "extensiones", length = 128)
    private String extensiones;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "ancho")
    private Integer ancho;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "alto")
    private Integer alto;

    @Column(name = "maximo_archivo")
    private Integer maximoArchivo;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    /**
	 * Relacion con Grupo-Archivo
	 */
	@OneToMany(targetEntity = Archivo.class, fetch = FetchType.LAZY)	
	@JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")	
	@OrderBy("usuarioFecha Asc")	
	private List<Archivo> ArchivoList;
}