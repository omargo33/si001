package com.qapaq.catalogos.jpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_notificacion_parametro, id_notificacion, llave, valor, tipo FROM GA_001_00.notificacion_parametro"
 * 
 * Con codigo autonumerico en la columna id_notificacion_parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-09
 * 
 */
@Entity
@Table(name = "notificacion_parametro", schema = "GA_001_00")
@Getter
@Setter
@ToString
public class NotificacionParametro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_parametro")
    private Long idNotificacionParametro;

    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @Column(name = "llave", length = 64)
    private String llave;

    @Column(name = "valor", length = 512)
    private String valor;

    @Column(name = "tipo", length = 8)
    private String tipo;
}
    
