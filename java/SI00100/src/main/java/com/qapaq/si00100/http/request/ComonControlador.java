package com.qapaq.si00100.http.request;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase para controlar las excepciones.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 */
public class ComonControlador {

    private Object[] arguments;

    /**
     * Metodo para informar errores de validacion.
     * 
     * Controla si el error es de validacion estandart de spring
     * Controla si el error es de validacion personalizada
     * 
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            String fieldName = "";
            try{
                fieldName = ((FieldError) error).getField();
            } catch (Exception e) {
                arguments = error.getArguments();
                if (arguments.length > 1) {
                    fieldName = String.valueOf(arguments[1]);
                } else {
                    fieldName = error.getObjectName();
                }
            }
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }    
}
