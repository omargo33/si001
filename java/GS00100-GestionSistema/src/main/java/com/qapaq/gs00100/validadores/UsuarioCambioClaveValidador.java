package com.qapaq.gs00100.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qapaq.ca00100.ConstantesCA00100;
import com.qapaq.ca00100.servicio.AuditoriaServicioCat;
import com.qapaq.gs00100.ConstantesGS00100;
import com.qapaq.gs00100.jpa.model.Parametro;
import com.qapaq.gs00100.jpa.pojo.UsuarioClave;
import com.qapaq.gs00100.servicio.ParametroServicio;

/**
 * Clase Validador para claves y nuevas claves.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-04-02
 * 
 */
@Component
public class UsuarioCambioClaveValidador implements ConstraintValidator<UsuarioCambioClave, UsuarioClave> {
    @Autowired
    private ParametroServicio parametroServicio;

    @Autowired
    private AuditoriaServicioCat auditoriaServicioCat;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void initialize(UsuarioCambioClave constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Metodo para validar que un cambio de clave es aceptado.
     * 
     * Guarda la auditoria del servicio antes que entre a validacion para que no se pierda la informacion de ingreso.
     * 
     * Si la clave actual es igual a la nueva clave, se valida que la nueva clave sea igual a la confirmacion de la nueva clave.
     * 
     * @param usuarioClave
     * @param context
     * @return
     */
    @Override
    public boolean isValid(UsuarioClave usuarioClave, ConstraintValidatorContext context) {
        if (parametroServicio == null) {
            return true;
        }

        auditoriaServicio(usuarioClave.getIp(), usuarioClave.getUserAgent(), usuarioClave.getSocialNick(),
                usuarioClave.getUsuarioPrograma());

        if (usuarioClave.getClaveActual().compareTo(usuarioClave.getClaveNueva()) == 0) {
            if (usuarioClave.getClaveNueva().compareTo(usuarioClave.getClaveNuevaConfirmacion()) != 0) {
                Parametro parametro = parametroServicio.findByIdModuloAndIndice(appName,
                        ConstantesGS00100.PARAMETRO_EXPRESION_REGULAR);
                Pattern pat = Pattern.compile(parametro.getValorTexto01());
                Matcher mat = pat.matcher(usuarioClave.getClaveNueva());
                return mat.matches();
            }
        }

        return false;
    }

    /**
     * Metodo para registrar la auditoria de un servicio.
     * 
     * @param ip
     * @param userAgent
     * @param usuario
     * @param usuarioPrograma
     */
    private void auditoriaServicio(String ip, String userAgent, String usuario, String usuarioPrograma) {
        auditoriaServicioCat.createAuditoria(appName, "<NO APLICA>", null, "cambiarTokenCompleto()", usuario,
                usuarioPrograma);
        auditoriaServicioCat.agregarEnvento(userAgent, ConstantesCA00100.AUDITORIA_EVENTO_TIPO_SEGURIDADES);
        auditoriaServicioCat.agregarParametro("nick", usuario, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("ip", ip, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("objeto", usuarioPrograma,
                ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
    }
}
