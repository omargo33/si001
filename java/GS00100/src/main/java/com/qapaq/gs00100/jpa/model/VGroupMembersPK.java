package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

/**
 * Clase que representa la clave primaria de la entidad VGroupMembers.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
public class VGroupMembersPK implements Serializable{
    
            private static final long serialVersionUID = 1L;
    private String name;    
    private String member;

    /**
     * Constructor por defecto.
     */
    public VGroupMembersPK() {
        super();
    }   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    /**
     * Metodo que genera el hashcode de la clave primaria.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        hash += (member != null ? member.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo que compara dos claves primarias.
     */
    @Override
    public boolean equals(Object object) {
        //Comparacion de referencias.
        if (this == object) {
            return true;
        }
        return false;
    }    
}
