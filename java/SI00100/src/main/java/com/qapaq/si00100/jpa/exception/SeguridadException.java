package com.qapaq.si00100.jpa.exception;

/**
 * Clase para crear un error personalizado para existencia para aseguramiento del aplicativo.
 * 
 * @author o.velez
 * @date 2020-10-09
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 */
public class SeguridadException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor de la clase.
     *    
     * @param message
     */
    public SeguridadException(String message) {
        super(message);                
    }    
}
