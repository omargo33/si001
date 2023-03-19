package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
public class VGroupMembers implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NAME")
    private String name;
    @Id
    @Column(name = "MEMBER")
    private String member;

}
