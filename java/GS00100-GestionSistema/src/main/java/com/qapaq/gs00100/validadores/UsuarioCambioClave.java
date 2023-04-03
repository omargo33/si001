package com.qapaq.gs00100.validadores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * Anotacion para validar cambio de clave.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-04-02
 * 
 */

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsuarioCambioClaveValidador.class)
@Documented
public @interface UsuarioCambioClave {

    String titulo() default "none";

    String message() default "none";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};
}
