package com.qapaq.si.background.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad creada desde la consulta: "SELECT id_auditoria_parametro,
 * id_auditoria, nombre, direccion, valor FROM AR_001_00.auditoria_parametro"
 * 
 * @author o.velez@qapaq.io
 * @date 2020-08-22
 * 
 */
@Entity
@Table(name = "auditoria_parametro", schema = "AR_001_00")
public class AuditoriaParametro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_auditoria_parametro")
    private Long idAuditoriaParametro;
    @Column(name = "id_auditoria")
    private Long idAuditoria;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "direccion", length = 8)
    private String direccion;
    @Column(name = "valor", length = 256)
    private String valor;

    public Long getIdAuditoriaParametro() {
        return idAuditoriaParametro;
    }

    public void setIdAuditoriaParametro(Long idAuditoriaParametro) {
        this.idAuditoriaParametro = idAuditoriaParametro;
    }

    public Long getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Long idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AuditoriaParametro [idAuditoriaParametro=" + idAuditoriaParametro + ", idAuditoria=" + idAuditoria
                + ", nombre=" + nombre + ", direccion=" + direccion + ", valor=" + valor + "]";
    }

}
