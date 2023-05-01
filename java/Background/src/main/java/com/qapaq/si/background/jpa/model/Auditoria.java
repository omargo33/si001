package com.qapaq.si.background.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Entidad creada desde la consulta "SELECT id_auditoria, nombre, valor_01, valor_02, usuario, usuario_fecha, usuario_programa, elemento FROM AR_001_00.auditoria"
 * 
 * @author o.velez@qapaq.io
 * @date 2020-08-22
 * 
 */
@Entity
@Table(name = "auditoria", schema = "AR_001_00")
public class Auditoria  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_auditoria")
    private Long idAuditoria;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "valor_01", length = 256)
    private String valor01;
    @Column(name = "valor_02", length = 256)
    private String valor02;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
    @Column(name = "elemento", length = 256)
    private String elemento;

    @ManyToOne
    @JoinColumn(name = "id_auditoria", referencedColumnName = "id_auditoria", insertable = false, updatable = false)
    private AuditoriaEvento auditoriaEvento;

    @ManyToOne
    @JoinColumn(name = "id_auditoria", referencedColumnName = "id_auditoria", insertable = false, updatable = false)
    private AuditoriaParametro auditoriaParametro;


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
    public String getValor01() {
        return valor01;
    }
    public void setValor01(String valor01) {
        this.valor01 = valor01;
    }
    public String getValor02() {
        return valor02;
    }
    public void setValor02(String valor02) {
        this.valor02 = valor02;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public Date getUsuarioFecha() {
        return usuarioFecha;
    }
    public void setUsuarioFecha(Date usuarioFecha) {
        this.usuarioFecha = usuarioFecha;
    }
    public String getUsuarioPrograma() {
        return usuarioPrograma;
    }
    public void setUsuarioPrograma(String usuarioPrograma) {
        this.usuarioPrograma = usuarioPrograma;
    }
    public String getElemento() {
        return elemento;
    }
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public AuditoriaEvento getAuditoriaEvento() {
        return auditoriaEvento;
    }   
    public void setAuditoriaEvento(AuditoriaEvento auditoriaEvento) {
        this.auditoriaEvento = auditoriaEvento;
    }

    public AuditoriaParametro getAuditoriaParametro() {
        return auditoriaParametro;
    }

    public void setAuditoriaParametro(AuditoriaParametro auditoriaParametro) {
        this.auditoriaParametro = auditoriaParametro;
    }

    @Override
    public String toString() {
        return "Auditoria [idAuditoria=" + idAuditoria + ", nombre=" + nombre + ", valor01=" + valor01 + ", valor02=" + valor02 + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + ", elemento=" + elemento + "]";
    }
}
