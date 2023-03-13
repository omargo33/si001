package com.qapaq.ga00100.jpa.model;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* 
* Entidad JPA creada desde la consulta: "SELECT id_archivo, id_grupo, nombre_ramdon, nombre, extension, largo, path_relativo, estado, usuario, usuario_fecha, usuario_programa FROM GA_001_00.archivo"
*
* Con codigo autonumerico en la columna id_archivo.
* 
* @author o.velez@qapaq.io
* @date 2023-03-09
* 
*/
@Entity
@Table(name = "archivo", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class Archivo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Long idArchivo;
    
    @NotBlank(message = "E-GA00100-12")
    @Column(name = "id_grupo", insertable = false, updatable = false)
    private Long idGrupo;

    @NotBlank(message = "E-GA00100-12")    
    @Column(name = "nombre_ramdon", length = 128)
    private String nombreRamdon;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "nombre", length = 128)
    private String nombre;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "extension", length = 16)
    private String extension;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "largo")
    private Long largo;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "path_relativo", length = 256)
    private String pathRelativo;

    @NotBlank(message = "E-GA00100-12")
    @Column(name = "estado", length = 16)
    private String estado;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;	
}
