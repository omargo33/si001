package com.qapaq.gs00101.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qapaq.gs00101.jpa.model.Modulo;
import com.qapaq.gs00101.servicio.ModuloServicio;

/**
 * Clase Validador para indice de modulo.
 * 
 * @author o.velez@qapaq.io
 * @since 2022-10-30
 * 
 */
@Component
public class ModuloIndiceValidator implements ConstraintValidator<ModuloIndice, Modulo> {
    @Autowired
    private ModuloServicio moduloService;

    @Override
    public void initialize(ModuloIndice constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Metodo para validar si exite un indice de modulo.
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

        if (modulo.getIndice() == null || modulo.getIndice().isEmpty()) {
            return true;
        }

        if (modulo.getIdModulo() == null || modulo.getIdModulo() <= 0) {
            return !moduloService.isIndiceUnico(modulo.getIndice());
        }

        return !moduloService.isIndiceUnico(modulo.getIndice(), modulo.getIdModulo());
    }
}