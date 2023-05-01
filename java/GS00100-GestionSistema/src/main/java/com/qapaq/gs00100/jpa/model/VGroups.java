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
@Table(name = "v_groups")
@Getter
@Setter
@ToString
public class VGroups implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NAME")
    private String name;
 
    @Id
    @Column(name = "DESCRIPTION")
    private String description;    
}