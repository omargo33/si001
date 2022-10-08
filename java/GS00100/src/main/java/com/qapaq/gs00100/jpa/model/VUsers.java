package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_usuario, DESCRIPTION, NAME, PASSWORD FROM GS_001_00.v_users;"
 * 
 * Con codigo autonumerico en la columna id_usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-11
 * 
 */
@Entity
@Table(name = "v_users", schema = "GS_001_00")
public class VUsers implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "description", length = 256)
    private String description;
    @Column(name = "name", length = 256)
    private String name;
    @Column(name = "password", length = 256)
    private String password;

    /**
     * Constructor por defecto.
     */
    public VUsers() {
        super();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
