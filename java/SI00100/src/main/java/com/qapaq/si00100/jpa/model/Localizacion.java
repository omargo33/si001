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
 * id_localizacion, id_ciudad, nombre, latitud, longitud, usuario,
 * usuario_fecha, usuario_programa FROM SI_001_00.localizacion;"
 * 
 * El indice es id_localizcion autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table(name = "localizacion", schema = "SI_001_00")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "usuario_programa" })
public class Localizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacion")
    private Long idLocalizacion;

    @Column(name = "id_ciudad")
    private Long idCiudad;

    @Column(name = "nombre", length = 128)
    private String nombre;

    @Column(name = "latitud", length = 64)
    private String latitud;

    @Column(name = "longitud", length = 64)
    private String longitud;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    /**
     * Relacion con Localizacion-Monitor
     */
    @OneToMany(targetEntity = Monitor.class)
    @JoinColumn(name = "id_localizacion", referencedColumnName = "id_localizacion")
    private List<Localizacion> localizacionList;
}
