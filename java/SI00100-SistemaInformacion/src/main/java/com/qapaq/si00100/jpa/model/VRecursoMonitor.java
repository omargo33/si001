package com.qapaq.si00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * Objeto para crear la entidad JPA en base a la consulta de la tabla de la consulta "SELECT id_v_recurso_monitor, indice, tipo, duracion, tipo_orientacion, sonido, brillo, relacion, definicion, qr, qr_posicion_x, qr_posicion_y, id_recurso, nombre, network, wifi FROM SI_001_00.v_recurso_monitor;"
 * 
 * El indice es id_recurso autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-24
 * 
 */
@Entity
@Table(name = "v_recurso_monitor", schema = "SI_001_00")
@Getter
@Setter
public class VRecursoMonitor implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id    
    @Column(name = "id_v_recurso_monitor", length = 128)
    private String idVRecursoMonitor;

    @Column(name = "indice", length = 64)
    private String indice;
    
    @Column(name = "tipo", length = 128)
    private String tipo;
    
    @Column(name = "duracion")
    private Long duracion;
    
    @Column(name = "tipo_orientacion", length = 128)
    private String tipoOrientacion;
    
    @Column(name = "sonido", length = 128)
    private String sonido;
    
    @Column(name = "brillo")
    private Long brillo;
    
    @Column(name = "relacion", length = 128)
    private String relacion;
    
    @Column(name = "definicion", length = 128)
    private String definicion;
    
    @Column(name = "qr", length = 128)
    private String qr;
    
    @Column(name = "qr_posicion_x")
    private Long qrPosicionX;
    
    @Column(name = "qr_posicion_y")
    private Long qrPosicionY;
    
    @Column(name = "id_recurso")
    private Long idRecurso;
    
    @Column(name = "nombre", length = 128)
    private String nombre;
    
    @Column(name = "network", length = 128)
    private String network;
    
    @Column(name = "wifi", length = 128)
    private String wifi;
}
