package com.qapaq.si00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qapaq.si00100.jpa.model.Informacion;
import com.qapaq.si00100.servicio.InformacionServicio;

/**
 * Clase para validar que el nombre de la informacion no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-07
 */
@Component
public class InformacionNombreValidator implements ConstraintValidator<InformacionNombre, Informacion> {
    
    @Autowired
    private InformacionServicio informacionServicio;
    
        @Override
        public void initialize(InformacionNombre constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }
    
         /**
     * Metodo para validar si existe un cliente con el correo.
     * 
     * @param cliente
     * @param context
     */
    @Override
    public boolean isValid(Informacion informacion, ConstraintValidatorContext context) { 
        if(informacionServicio == null) {
            return true;
        }

        if (informacion.getNombre() == null || informacion.getNombre().isEmpty()) {
            return true;
        }

        if (informacion.getIdInformacion() == null ||  informacion.getIdInformacion() <= 0) {                        
            
            return !informacionServicio.isInformacionUnico(informacion.getNombre());
        }

        return !informacionServicio.isInformacionUnico(informacion.getNombre(), informacion.getIdInformacion());
    }    
}
