package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Clase JPA para la tabla modulo de la consulta "SELECT NAME, MEMBER FROM GS_001_00.v_group_members;"
 * 
 * Con codigo  columna NAME Y MEMBER.
 * 
 * @author o.velez@qapaq.io
 * @date 2018-10-10
 * 
 */
@Entity
@IdClass(VGroupMembersPK.class)
@Table(name = "v_group_members", schema = "GS_001_00")
public class VGroupMembers implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NAME")
    private String name;
    @Id
    @Column(name = "MEMBER")
    private String member;

    /**
     * Metodo para crear una instancia de la clase VGroupMembers.
     */
    public VGroupMembers() {
        super();
    }

    public VGroupMembers(String name, String member) {
        this.name = name;
        this.member = member;
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
     * Metodo para sobrecargar el metodo toString de la clase Object.
     */
    @Override
    public String toString(){
        return "VGroupMembers [name=" + name + ", member=" + member + "]";
    }
}
