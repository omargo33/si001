package com.qapaq.gs00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qapaq.gs00100.jpa.model.Usuario;
import com.qapaq.gs00100.servicio.UsuarioServicio;

/**
 * Clase Validador para nick de usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-30
 * 
 */
@Component
public class UsuarioNickValidator implements ConstraintValidator<UsuarioNick, Usuario> {
    @Autowired
    private UsuarioServicio usuarioService;

    @Override
    public void initialize(UsuarioNick constraintAnnotation) {
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
    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        if (usuarioService == null) {
            return true;
        }

        if (usuario.getNick() == null || usuario.getNick().isEmpty()) {
            return true;
        }

        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() <= 0) {
            return !usuarioService.isNickUnico(usuario.getNick());
        }

        return !usuarioService.isNickUnico(usuario.getNick(), usuario.getIdUsuario());
    }
}