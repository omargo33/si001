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
 * Entidad creada para la consulta "SELECT id_monitor, id_localizacion, nombre,
 * clave, serie, descripcion, tipo_sistema_operativo, version, tipo_orientacion,
 * network, wifi, ip, puerto, relacion, definicion, clima, hora, debug, usuario,
 * usuario_fecha, usuario_programa FROM SI_001_00.monitor;"
 * 
 * 
 * @author o.velez@qapaq.io
 * @date 2022/08/14
 * 
 */

@Entity
@Table(name = "monitor", schema = "SI_001_00")
public class Monitor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_monitor")
    private Long idMonitor;
    @Column(name = "id_localizacion")
    private Long idLocalizacion;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "clave", length = 128)
    private String clave;
    @Column(name = "serie", length = 128)
    private String serie;
    @Column(name = "descripcion", length = 256)
    private String descripcion;
    @Column(name = "tipo_sistema_operativo", length = 128)
    private String tipoSistemaOperativo;
    @Column(name = "version", length = 128)
    private String version;
    @Column(name = "tipo_orientacion", length = 128)
    private String tipoOrientacion;
    @Column(name = "network", length = 128)
    private String network;
    @Column(name = "wifi", length = 128)
    private String wifi;
    @Column(name = "ip", length = 128)
    private String ip;
    @Column(name = "puerto", length = 128)
    private String puerto;
    @Column(name = "relacion", length = 128)
    private String relacion;
    @Column(name = "definicion", length = 128)
    private String definicion;
    @Column(name = "clima", length = 128)
    private String clima;
    @Column(name = "hora", length = 128)
    private String hora;
    @Column(name = "debug", length = 128)
    private String debug;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @ManyToOne
    @JoinColumn(name = "id_localizacion", referencedColumnName = "id_localizacion", insertable = false, updatable = false)
    private Localizacion localizacion;

    /**
     * Metodo para crear el objeto.
     * 
     */
    public static Monitor create() {
        return new Monitor();
    }

    /**
     * Crear getters and setters
     * 
     */
    public Long getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(Long idMonitor) {
        this.idMonitor = idMonitor;
    }

    public Long getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Long idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoSistemaOperativo() {
        return tipoSistemaOperativo;
    }

    public void setTipoSistemaOperativo(String tipoSistemaOperativo) {
        this.tipoSistemaOperativo = tipoSistemaOperativo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTipoOrientacion() {
        return tipoOrientacion;
    }

    public void setTipoOrientacion(String tipoOrientacion) {
        this.tipoOrientacion = tipoOrientacion;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
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

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * ToString del objeto.
     */
    @Override
    public String toString() {
        return "Monitor [idMonitor=" + idMonitor + ", idLocalizacion=" + idLocalizacion + ", nombre=" + nombre
                + ", clave=" + clave + ", serie=" + serie + ", descripcion=" + descripcion + ", tipoSistemaOperativo="
                + tipoSistemaOperativo + ", version=" + version + ", tipoOrientacion=" + tipoOrientacion + ", network="
                + network + ", wifi=" + wifi + ", ip=" + ip + ", puerto=" + puerto + ", relacion=" + relacion
                + ", definicion=" + definicion + ", clima=" + clima + ", hora=" + hora + ", debug=" + debug
                + ", usuario=" + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma
                + "]";
    }
}
