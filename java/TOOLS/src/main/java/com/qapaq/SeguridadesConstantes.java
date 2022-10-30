package com.qapaq;

/**
 *  Constantes de seguridades.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-28
 * 
 */
public class SeguridadesConstantes {
    private static final String TOKEN_SECRET="qapaq.io";
    public static final String ACCESS_TOKEN="access-token";
    public static final String REFRESH_TOKEN="refresh-token";
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";  
    
    /**
     * Constructor de la clase.
     */
    protected SeguridadesConstantes() { 
    }    

    /**
     * Método para obtener el token Secret.
     * @return
     */
    public static byte[] getTokenSecret() {
        return TOKEN_SECRET.getBytes();
    }
}