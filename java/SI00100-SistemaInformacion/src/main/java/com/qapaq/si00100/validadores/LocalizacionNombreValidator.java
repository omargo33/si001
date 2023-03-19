package com.qapaq.si00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qapaq.si00100.jpa.model.Localizacion;
import com.qapaq.si00100.servicio.LocalizacionServicio;

/**
 * Clase para validar que el nombre de la informacion no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-07
 */
@Component
public class LocalizacionNombreValidator implements ConstraintValidator<LocalizacionNombre, Localizacion> {

    @Autowired
    private LocalizacionServicio localizacionServicio;

    @Override
    public void initialize(LocalizacionNombre constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Metodo para validar si existe un localizacion con el nombre.
     * 
     * @param localizacion
     * @param context
     */
    @Override
    public boolean isValid(Localizacion localizacion, ConstraintValidatorContext context) {
        if (localizacionServicio == null) {
            return true;
        }

        if (localizacion.getNombre() == null || localizacion.getNombre().isEmpty()) {
            return true;
        }

        if (localizacion.getIdLocalizacion() == null || localizacion.getIdLocalizacion() <= 0) {
            return !localizacionServicio.isLocalizacionUnico(localizacion.getNombre());
        }

        return !localizacionServicio.isLocalizacionUnico(localizacion.getNombre(), localizacion.getIdLocalizacion());
    }
}
