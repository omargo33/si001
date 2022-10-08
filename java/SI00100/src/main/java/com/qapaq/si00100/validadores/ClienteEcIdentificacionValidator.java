package com.qapaq.si00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.qapaq.validaciones.Validaciones;

/**
 * Clase para validar que la idenficacion no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 */

public class ClienteEcIdentificacionValidator implements ConstraintValidator<ClienteEcIdentificacion, String> {

    /**
     * Metodo para validar si existe un cliente con el correo.
     * 
     * @param cliente
     * @param context
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) { 

        if(value.length()==13){
            return Validaciones.validarRuc(value);
        }

        if(value.length()==10){
            return Validaciones.validarCedula(value);
        }
        
        return Validaciones.validarPasaporte(value);
    }
}