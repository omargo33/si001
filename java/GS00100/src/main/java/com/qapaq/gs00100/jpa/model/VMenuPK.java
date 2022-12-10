package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;

/**
 * Clase que representa la clave primaria de la entidad VMenu.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
public class VMenuPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idMenu;    
    private String nick;

    /**
     * Constructor por defecto.
     */
    public VMenuPK() {
        super();
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Metodo que genera el hashcode de la clave primaria.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        hash += (nick != null ? nick.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo que compara dos claves primarias.
     */
    @Override
    public boolean equals(Object object) {
        //Comparacion de referencias.
        return (this == object);
    }
}
