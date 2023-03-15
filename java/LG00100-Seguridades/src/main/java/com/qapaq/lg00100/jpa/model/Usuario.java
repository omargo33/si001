package com.qapaq.lg00100.jpa.model;

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
 * Entidad JPA creada desde la consulta: "SELECT id_usuario, id_archivo, nick, nombre, apellido, usuario, validador, usuario_fecha, usuario_programa, estado, contador_ingreso, contador_fecha FROM GS_001_00.usuario;"
 * 
 * Con codigo autonumerico en la columna id_usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "usuario", schema = "GS_001_00")
@Getter
@Setter
@ToString
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Column(name = "id_archivo")
    private Long idArchivo;
   
    
    @Column(name = "nick", length = 128)
    private String nick;
   
    
    @Column(name = "nombre", length = 128)
    private String nombre;
   
    @Column(name = "apellido", length = 128)
    private String apellido;
   
   
    @Column(name = "usuario", length = 128)
    private String usuarioManager;

    @Column(name = "validador", length = 128)
    private String validador;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    @Column(name = "estado", length = 128)
    private String estado;

    @Column(name = "contador_ingreso")
    private Integer contadorIngreso;
    
    @Column(name = "contador_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contadorFecha;    
}
