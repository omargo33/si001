package com.qapaq.si00100.validadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * Clase para validar la identificacion para ec.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 * @see https://phoenixnap.com/kb/spring-boot-validation-for-rest-services
 * 
 */
@Target({ ElementType.FIELD  })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClienteEcIdentificacionValidator.class)
public @interface ClienteEcIdentificacion {    
        String message() default "none";
    
        Class<?>[] groups() default {};
    
        Class<?>[] payload() default {};
    
}