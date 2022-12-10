package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

/**
 * Clase que representa la clave primaria de la entidad VGroups.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
public class VGroupsPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String description;

    /**
     * Constructor por defecto.
     */
    public VGroupsPK() {
        super();
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

    /**
     * Metodo que genera el hashcode de la clave primaria.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        hash += (description != null ? description.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo que compara dos claves primarias.
     */
    @Override
    public boolean equals(Object object) {
        // Comparacion de referencias.
        return (this == object);
    }
}