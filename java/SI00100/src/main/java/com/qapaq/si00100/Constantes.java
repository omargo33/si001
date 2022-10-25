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
    public static final String MAC_ADDRES="^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";    
    public static final String IP_ADDRESS="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    public static final String TOKEN_SECRET="qapaq.io";
    public static final String ACCESS_TOKEN="access-token";
    public static final String REFRESH_TOKEN="refresh-token";
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";    
}
