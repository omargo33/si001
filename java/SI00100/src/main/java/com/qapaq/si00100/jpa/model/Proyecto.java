package com.qapaq.si00100.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad jpa en base a la consulta de la tabla "SELECT
 * id_proyecto, id_cliente, nombre, fecha_inicio, fecha_fin, estado, usuario,
 * usuario_fecha, usuario_programa FROM SI_001_00.proyecto;"
 * 
 * El indice es id_proyecto autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "proyecto", schema = "SI_001_00")
@Getter
@Setter
@ToString
@JsonIgnoreProperties ({ "estado", "usuario_programa" })
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre", length = 128)
    private String nombre;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    @Column(name = "estado", length = 8)
    private String estado;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    /**
     * Relacion con Proyecto-Recurso
     */
    @OneToMany(targetEntity = Recurso.class)
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    private List<Recurso> recursoList;
}