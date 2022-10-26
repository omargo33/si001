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
 * Objeto para crear la entidad JPA en base a la consulta de la tabla "SELECT id_monitor, id_localizacion, nombre, clave, serie, descripcion, tipo_sistema_operativo, version, tipo_orientacion, network, wifi, ip, puerto, relacion, definicion, clima, hora, debug, estado, usuario, usuario_fecha, usuario_programa FROM SI_001_00.monitor;"
 * 
 * El indice es id_monitor autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 */
@Entity
@Table (name = "monitor" , schema = "SI_001_00")
@Getter
@Setter
@ToString
@JsonIgnoreProperties ({ "usuario_programa" })
public class Monitor implements Serializable{        
        private static final long serialVersionUID = 1L;
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "id_monitor")
        private Long idMonitor;

        @Column (name = "id_localizacion")
        private Long idLocalizacion;

        @Column (name = "nombre", length = 128)
        private String nombre;

        @Column (name = "clave", length = 128)
        private String clave;

        @Column (name = "serie", length = 256)
        private String serie;

        @Column (name = "descripcion", length = 256)
        private String descripcion;

        @Column (name = "tipo_sistema_operativo", length = 8)
        private String tipoSistemaOperativo;

        @Column (name = "version", length = 64)
        private String version;

        @Column (name = "tipo_orientacion", length = 8)
        private String tipoOrientacion;

        @Column (name = "network", length = 128)
        private String network;

        @Column (name = "wifi", length = 128)
        private String wifi;

        @Column (name = "ip", length = 64)
        private String ip;

        @Column (name = "puerto", length = 8)
        private String puerto;

        @Column (name = "relacion", length = 8)
        private String relacion;

        @Column (name = "definicion", length = 8)
        private String definicion;

        @Column (name = "clima", length = 8)
        private String clima;

        @Column (name = "hora", length = 8)
        private String hora;

        @Column (name = "debug", length = 8)
        private String debug;

        @Column (name = "estado", length = 8)
        private String estado;

        @Column(name = "usuario", length = 128)
        private String usuario;

        @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
        @Temporal(TemporalType.TIMESTAMP)
        private Date usuarioFecha;

        @Column(name = "usuario_programa", length = 256)
        private String usuarioPrograma;

        /**
         * Relacion con Monitor-RecursoMonitor
         */
        @OneToMany(targetEntity = RecursoMonitor.class)
        @JoinColumn(name = "id_monitor", referencedColumnName = "id_monitor")
        private List<RecursoMonitor> recursosMonitorList;

        /**
         * Relacion con Monitor-MonitorAlerta
         */
        @OneToMany(targetEntity = MonitorAlerta.class)
        @JoinColumn(name = "id_monitor", referencedColumnName = "id_monitor")
        private List<MonitorAlerta> monitorAlertaList;

        /**
         * Relacion con Monitor-MonitorComando
         */
        @OneToMany(targetEntity = MonitorComando.class)
        @JoinColumn(name = "id_monitor", referencedColumnName = "id_monitor")
        private List<MonitorComando> monitorComandoList;

         
}
