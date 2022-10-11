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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qapaq.gs00100.seguridad.AESUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase JPA para la tabla modulo de la consulta "SELECT id_modulo, indice,
 * nombre, contexto, usuario, usuario_fecha, usuario_programa, estado FROM
 * GS_001_00.modulo";
 * 
 * Con codigo autonumerico en la columna id_modulo.
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "modulo", schema = "GS_001_00")
@JsonIgnoreProperties({ "idModulo", "estado", "usuario", "usuarioFecha" })
public class Modulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_modulo")
    private Long idModulo;
    
    @Transient
    @JsonProperty("idxModulo")
    private String idxModulo;

    @Column(name = "indice", length = 32)
    private String indice;
    @Column(name = "nombre", length = 128)
    private String nombre;
    @Column(name = "contexto", length = 128)
    private String contexto;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
    @Column(name = "estado", length = 8)
    private String estado;

    @OneToMany(targetEntity = CodigoDefinidoUsuario.class)
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    private List<CodigoDefinidoUsuario> codigoDefinidoUsuarioList;

    @OneToMany(targetEntity = Parametro.class)
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    private List<Parametro> parametroList;

    @OneToMany(targetEntity = Menu.class)
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    private List<Menu> menuList;

    @OneToMany(targetEntity = Rol.class)
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    private List<Rol> rolList;

    /**
     * Metodo para obtener el valor de la propiedad idModuloCustom
     * 
     * @return
     */
    public String getIdxModulo() {
        return AESUtil.encriptar(String.valueOf(idxModulo));
    }

    /**
     * Metodo para asignar el valor de la propiedad idModuloCustom
     * 
     * @param idxModuloCustom
     */
    public void setIdxModulo(String idxModulo) {
        this.idxModulo = idxModulo;
    }
}