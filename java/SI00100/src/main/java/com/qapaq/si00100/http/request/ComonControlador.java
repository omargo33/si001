package com.qapaq.si00100.http.request;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qapaq.si00100.jpa.exception.ForeignKeyException;

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
    @ExceptionHandler( value ={MethodArgumentNotValidException.class, ForeignKeyException.class, EmptyResultDataAccessException.class}) 
    public Map<String, String> handleExceptions(Object ex) {
        Map<String, String> errors = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException){
            return validationExceptions((MethodArgumentNotValidException) ex);
        }

        if (ex instanceof ForeignKeyException){
            return foreignKeyExceptions((ForeignKeyException) ex);
            
        }

        if(ex instanceof EmptyResultDataAccessException){
            return emptyResultDataAccessException();
        }
        
        return errors;
    }    

    /**
     * Metodo para informar errores de validacion estandar de spring.
     * 
     * @param ex
     * @return
     */
    private Map<String, String> validationExceptions(MethodArgumentNotValidException ex) {
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

    /**
     * Metodo para informar errores de validacion personalizada de llaves foraneas.
     * 
     * @param ex
     * @return
     */     
    private Map<String, String> foreignKeyExceptions(ForeignKeyException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(getControllerMapping(), ex.getMessage());
        return errors;
    }

    /**
     * Metodo para informar errores de no hay resultados al SQL.
     */
    private Map<String, String> emptyResultDataAccessException() {        
        Map<String, String> errors = new HashMap<>();        
        errors.put(getControllerMapping(), "E-SI00100-8");
        return errors;
    }

    /**
     * Metodo para obtener el nombre del RequestMapping de la clase.
     */
    private String getControllerMapping() {
        String controllerMapping = this.getClass().getAnnotation(RequestMapping.class).value()[0];
        controllerMapping = controllerMapping.replace("/", "");
        return controllerMapping;
    }
}