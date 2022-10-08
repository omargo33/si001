package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Clase JPA para la tabla modulo de la consulta "SELECT NAME, DESCRIPTION FROM GS_001_00.v_groups;"
 * 
 * Con codigo  columna NAME y DESCRIPTION.
 * 
 * @author o.velez@qapaq.io
 * @date 2018-10-10
 * 
 */
@Entity
@IdClass(VGroupsPK.class)
@Table(name = "v_groups", schema = "GS_001_00")
public class VGroups implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NAME")
    private String name;
    @Id
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Metodo para crear una instancia de la clase VGroups.
     */
    public VGroups() {
        super();
    }

    public VGroups(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}