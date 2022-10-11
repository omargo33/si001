package com.qapaq.si00100;

/**
 * Clase para crear constantes de la aplicacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 */
public class Constantes {

    /**
     * Constructor de la clase.
     */
    private Constantes() {             
    }

    public static final String LATITUD_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    public static final String LONGITUD_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";    
}