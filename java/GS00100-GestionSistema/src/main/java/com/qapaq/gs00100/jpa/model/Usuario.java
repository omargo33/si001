package com.qapaq.gs00100.jpa.model;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qapaq.gs00100.validadores.UsuarioNick;

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
@Table(name = "usuario")
@Getter
@Setter
@ToString
@UsuarioNick(titulo = "nick", message = "E-GS00100-6")
@JsonIgnoreProperties({"usuarioFecha", "estado", "contadorIngreso", "contadorFecha", "validador"})
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Column(name = "id_archivo")
    private Long idArchivo;
   
    @NotNull(message = "E-GS00100-2")
    @NotBlank(message = "E-GS00100-3")
    @Column(name = "nick", length = 128)
    private String nick;
   
    @NotNull(message = "E-GS00100-2")
    @NotBlank(message = "E-GS00100-3")
    @Column(name = "nombre", length = 128)
    private String nombre;
   
    @NotNull(message = "E-GS00100-2")
    @NotBlank(message = "E-GS00100-3")
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

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @OneToMany
    private List<Token> tokenList;
}
