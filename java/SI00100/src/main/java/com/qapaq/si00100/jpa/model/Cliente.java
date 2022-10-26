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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qapaq.si00100.validadores.ClienteCorreo;
import com.qapaq.si00100.validadores.ClienteEcIdentificacion;
import com.qapaq.si00100.validadores.ClienteIdentificacion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto para crear la entidad jpa en base a la consulta de la tabla "SELECT
 * id_cliente, razon_social, identificacion, estado, correo, usuario,
 * usuario_fecha, usuario_programa FROM SI_001_00.cliente;"
 * 
 * El indice es id_cliente autoincremental.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-02
 * 
 * @see https://phoenixnap.com/kb/spring-boot-validation-for-rest-services
 * 
 */
@Entity
@Table(name = "cliente", schema = "SI_001_00")
@Getter
@Setter
@ToString
@ClienteCorreo(titulo = "correo", message = "E-SI00100-3")
@ClienteIdentificacion(titulo = "identificacion", message = "E-SI00100-4")
@JsonIgnoreProperties({"estado", "usuario_programa" })
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @NotBlank(message = "E-SI00100-12")
    @Column(name = "razon_social", length = 256)
    private String razonSocial;

    @NotBlank(message = "E-SI00100-12")
    @ClienteEcIdentificacion(message = "E-SI00100-5")
    @Column(name = "identificacion", length = 16)
    private String identificacion;

    @Column(name = "estado", length = 8)
    private String estado;

    @NotBlank(message = "E-SI00100-12")
    @Email(message = "E-SI00100-1")    
    @Column(name = "correo", length = 256)    
    private String correo;

    @Column(name = "usuario", length = 128)
    private String usuario;

    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;

    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;

    /**
     * Relacion con Cliente-Proyecto
     */
    @OneToMany(targetEntity = Proyecto.class)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private List<Proyecto> proyectoList;
}