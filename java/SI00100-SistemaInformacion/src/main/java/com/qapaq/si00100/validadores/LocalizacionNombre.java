package com.qapaq.si00100.validadores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * Clase para validar que el nombre de la Localizacion no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-11
 * 
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalizacionNombreValidator.class)
@Documented
public @interface LocalizacionNombre {
    String titulo() default "none";

    String message() default "none";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};
}