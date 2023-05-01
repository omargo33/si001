package com.qapaq.si.background.jpa.model;

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

/**
 * Entidad creada desde la consulta "SELECT id_auditoria_evento, id_auditoria,
 * descripcion, tipo, orden, parte, usuario_fecha FROM
 * AR_001_00.auditoria_evento"
 * 
 * @author o.velez@qapaq.io
 * @date 2020-08-22
 * 
 */
@Entity
@Table(name = "auditoria_evento", schema = "AR_001_00")
public class AuditoriaEvento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_auditoria_evento")
    private Long idAuditoriaEvento;
    @Column(name = "id_auditoria")
    private Long idAuditoria;
    @Column(name = "descripcion", length = 512)
    private String descripcion;
    @Column(name = "tipo", length = 8)
    private String tipo;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "parte")
    private String parte;
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    public Long getIdAuditoriaEvento() {
        return idAuditoriaEvento;
    }

    public void setIdAuditoriaEvento(Long idAuditoriaEvento) {
        this.idAuditoriaEvento = idAuditoriaEvento;
    }

    public Long getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Long idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public Date getUsuarioFecha() {
        return usuarioFecha;
    }

    public void setUsuarioFecha(Date usuarioFecha) {
        this.usuarioFecha = usuarioFecha;
    }

    /**
     * Metodo sobrecargado para toString().
     * 
     */
    @Override
    public String toString() {
        return "AuditoriaEvento [idAuditoriaEvento=" + idAuditoriaEvento + ", idAuditoria=" + idAuditoria
                + ", descripcion=" + descripcion + ", tipo=" + tipo + ", orden=" + orden + ", parte=" + parte
                + ", usuarioFecha=" + usuarioFecha + "]";
    }
}
