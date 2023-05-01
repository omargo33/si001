package com.qapaq.ar00100.jpa.model;

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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_auditoria_evento, id_auditoria, descripcion, tipo, orden, parte, usuario_fecha FROM AR_001_00.auditoria_evento;"
"
 * 
 * Con codigo autonumerico en la columna id_auditoria_evento.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
@Entity
@Table(name = "auditoria_evento", schema = "AR_001_00")
@Getter
@Setter
@ToString
public class AuditoriaEvento  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria_evento")
    private Long idAuditoriaEvento;

    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @Column(name = "descripcion", length = 256)
    private String descripcion;

    @Column(name = "tipo", length = 32)
    private String tipo;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "parte")
    private Integer parte;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;    
}
