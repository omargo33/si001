package com.qapaq.gs00101.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qapaq.gs00101.jpa.model.Modulo;
import com.qapaq.gs00101.servicio.ModuloServicio;

/**
 * Clase Validador para nombre de modulo.
 * 
 * @author o.velez@qapaq.io
 * @since 2022-10-30
 * 
 */
@Component
public class ModuloNombreValidator implements ConstraintValidator<ModuloNombre, Modulo> {
    @Autowired
    private ModuloServicio moduloService;

    @Override
    public void initialize(ModuloNombre constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Metodo para validar si exite un nombre de modulo.
     * 
     * @param modulo
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Modulo modulo, ConstraintValidatorContext context) {
        if (moduloService == null) {
            return true;
        }

        if (modulo.getNombre() == null || modulo.getNombre().isEmpty()) {
            return true;
        }

        if (modulo.getIdModulo() == null || modulo.getIdModulo() <= 0) {
            return !moduloService.isNombreUnico(modulo.getNombre());
        }

        return !moduloService.isNombreUnico(modulo.getNombre(), modulo.getIdModulo());
    }
}