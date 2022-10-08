package com.qapaq.si00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qapaq.si00100.jpa.model.Ciudad;
import com.qapaq.si00100.servicio.CiudadServicio;


/**
 * Clase para validar que el nombre de la ciudad no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-07
 */
@Component
public class CiudadNombreValidator implements ConstraintValidator<CiudadNombre, Ciudad> {
    
    @Autowired
    private CiudadServicio ciudadServicio;
    
        @Override
        public void initialize(CiudadNombre constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }
    
         /**
     * Metodo para validar si existe un cliente con el correo.
     * 
     * @param cliente
     * @param context
     */
    @Override
    public boolean isValid(Ciudad ciudad, ConstraintValidatorContext context) { 
        if (ciudad.getNombre() == null || ciudad.getNombre().isEmpty()) {
            return true;
        }       

        if(ciudadServicio == null) {
            return true;
        }
        
        if (ciudad.getNombre().length() > 0) {            
            return !ciudadServicio.isCiudadUnico( ciudad.getNombre());
        }

        return !ciudadServicio.isCiudadUnico(ciudad.getNombre(), ciudad.getIdCiudad());
    }    
}
