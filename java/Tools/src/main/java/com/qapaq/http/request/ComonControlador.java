package com.qapaq.http.request;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qapaq.jpa.exception.ForeignKeyException;
import org.springframework.dao.DataIntegrityViolationException;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase para controlar las excepciones.
 * 
 * @author o.velez@qapaq.io
 * @since 2020-10-04
 * @see https://www.baeldung.com/spring-keycloak-get-user-id
 * 
 */
@Slf4j
public class ComonControlador {

    private Object[] arguments;

    @Value("${spring.application.name}")
    private String appName;


    /**
     * Metodo para informar errores de validacion.
     * 
     * Controla si el error es de validacion estandar de spring
     * Controla si el error es de validacion personalizada
     * 
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            ForeignKeyException.class,
            EmptyResultDataAccessException.class,
            HttpMessageNotReadableException.class,
            DataIntegrityViolationException.class })
    public Map<String, String> handleExceptions(Object ex) {
        Map<String, String> errors = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
            return validationExceptions((MethodArgumentNotValidException) ex);
        }

        if (ex instanceof ForeignKeyException) {
            return foreignKeyExceptions((ForeignKeyException) ex);
        }

        if (ex instanceof EmptyResultDataAccessException) {
            return emptyResultDataAccessException();
        }

        if (ex instanceof HttpMessageNotReadableException) {
            return httpMessageNotReadableException((HttpMessageNotReadableException) ex);
        }
        
        if (ex instanceof DataIntegrityViolationException) {
            return sqlIntegrityConstraintViolationException((DataIntegrityViolationException) ex);
        }

        log.error("W-GS00100-2 {}", ex.getClass().getName());

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
            try {
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
        errors.put(getControllerMapping(), "E-GS00100-8");
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

    /**
     * Metodo para conocer el usuario que realizar una interaccion.
     * 
     * @param ex
     * @return
     */
    public String evaluarUsuario(HttpServletRequest request) {
        String nombre = "none";
        //String authorizationHeader = request.getHeader(ConstantesTools.HEADER_STRING);

        //TODO cambio de seguridad por keycloak
        /*
        if (authorizationHeader != null && authorizationHeader.startsWith(ConstantesTools.TOKEN_PREFIX)) {
            String token = authorizationHeader.substring(ConstantesTools.TOKEN_PREFIX.length());
            Algorithm algorithm = Algorithm.HMAC256(ConstantesTools.getPassword());
            JWTVerifier verifier = JWT.require(algorithm).build();
            try {
                DecodedJWT decodedJWT = verifier.verify(token);
                nombre = decodedJWT.getSubject();
            } catch (JWTDecodeException e) {
                log.error("W-GS00100-1 {}", e.toString());
                nombre = "none";
            }
        }
         */
        return nombre;
    }

    /**
     * Metodo para informar errores de validacion personalizada de formato de fechas
     * de rest.
     * 
     * Y los demas errores dejar que los maneje el estandar de spring.
     * 
     * @param ex
     * @return
     */
    private Map<String, String> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        if (ex.toString().lastIndexOf("java.util.Date") > 1) {
            errors.put(getControllerMapping(), "E-GS00100-18");
        } else {
            throw new IllegalStateException(ex.toString());
        }
        return errors;
    }

    /**
     * Metodo para informar errores de validacion personalizada de Constrain de foreign de tabla de SQL.
     * 
     * @param ex
     * @return
     */
    private Map<String, String> sqlIntegrityConstraintViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();        
        errors.put(getControllerMapping(), "E-GS00100-19");
        return errors;
    }

    /**
     * Metodo para crear el nombre de aplicacion con path correcto.
     * 
     * @param usuarioPrograma
     * @return
     * 
     */
    public String getUsuarioPrograma(String usuarioPrograma) {
        if (usuarioPrograma == null || usuarioPrograma.isEmpty()) {
            usuarioPrograma = "noCliente@" + appName  ;
            return usuarioPrograma;
        }

        int separador = usuarioPrograma.indexOf("@");
        if (separador > 0) {
            usuarioPrograma = usuarioPrograma.substring(0, separador) + "@" + appName  ;
        } else {
            usuarioPrograma = usuarioPrograma + "@" + appName;
        }

        return usuarioPrograma;
    }
}
