package com.qapaq.gs00100.servicio;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ar00100.ContantesAR00100;
import com.qapaq.ar00100.servicio.AuditoriaServicio;
import com.qapaq.gs00100.ConstantesGS00100;
import com.qapaq.gs00100.jpa.model.Token;
import com.qapaq.gs00100.jpa.queries.TokenRepositorio;
import com.qapaq.security.GeneradorClaves;
import com.qapaq.security.Hash;

import lombok.extern.slf4j.Slf4j;

/**
 * Objeto para dar soporte a servicio REST de token.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-30
 * 
 */
@Service
@Transactional("gs001001TransactionManager")
@Slf4j
public class TokenServicio {

    @Value("${app.name}")
    private String appName;

    private final AuditoriaServicio auditoriaServicio;
    private final TokenRepositorio tokenRepositorio;
    private final ParametroServicio parametroServicio;

    private final PasswordEncoder passwordEncoder;

    /**
     * Metodo para crear los repositorios.
     * 
     * @param moduloRepositorio
     *
     */
    @Autowired
    public TokenServicio(AuditoriaServicio auditoriaServicio, TokenRepositorio tokenRepositorio,
            ParametroServicio parametroServicio, PasswordEncoder passwordEncoder) {
        this.auditoriaServicio = auditoriaServicio;
        this.tokenRepositorio = tokenRepositorio;
        this.parametroServicio = parametroServicio;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Metodo para buscar por idToken.
     * 
     */
    public Token findByIdToken(Long idToken) {
        return tokenRepositorio.findByIdToken(idToken);
    }

    /**
     * Metodo para buscar por idUsuario.
     * 
     */
    public List<Token> findByIdUsuario(Long idUsuario, Pageable pageable) {
        return tokenRepositorio.findByIdUsuario(idUsuario, pageable);
    }

    /**
     * Metodo para guardar un token.
     * 
     * @param token
     * @return
     */
    public Token guardarToken(Token token, String usuario, String usuarioPrograma) {
        token.setEstado(ConstantesGS00100.TOKEN_ESTADO_ACTIVO);
        token.setValidador(
                Hash.crearHash(String.valueOf(token.getIdUsuario()), token.getTipo(), token.getSocialNick()));
        token.setUsuario(StringUtils.truncate(usuario, 128));
        token.setUsuarioFecha(new Date());
        token.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        return tokenRepositorio.save(token);
    }

    /**
     * Metodo para borrar un token de manera logico.
     * 
     * @param idToken
     */
    public void deleteByIdToken(Long idToken) {
        tokenRepositorio.deleteByIdToken(idToken);
    }

    /**
     * Metodo para buscar un token por socialNick y tipo.
     * 
     * @param socialNick
     * @param tipo
     */
    public Token findBySocialNickAndTipo(String socialNick, String tipo) {
        return tokenRepositorio.findBySocialNickAndTipo(socialNick, tipo);
    }

    /**
     * Metodo para enviar token y enviar por mail.
     * 
     * Busca token por correo.
     * Si no existe, crea una entrada en la auditoria
     * Si existe:
     * Toma el social nick desde el token
     * Crea un password de forma aleatoria
     * 
     * Optiene el parametro con el indice 100
     * Actualizo:
     * Token con el password
     * Estado = C
     * 
     * Toma el usuario desde el token
     * Envia notificacion por mail
     */
    public boolean enviarToken(String correo, String ip, String userAgent, String usuarioPrograma) {
        Token token = tokenRepositorio.findByCorreo(correo);
        if (token == null) {
            auditarSolicitudesFallidos(correo, ip, userAgent, usuarioPrograma);
            return false;
        }

        String password = GeneradorClaves.getPassword(GeneradorClaves.KEY_ALFANUMERICOS, 10);
        token.setEstado(ConstantesGS00100.TOKEN_ESTADO_CREADO);
        token.setTokenPassword(passwordEncoder.encode(password));
        token.setUsuarioFecha(new Date());
        token.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        tokenRepositorio.save(token);

        //TODO: Enviar correo
        log.warn("Se envia correo a: {} con el password {} ",  correo, password);
        return true;
    }

    /**
     * Metodo para auditar el error de ingreso.
     * 
     * @param correo
     * @param ip
     * @param userAgent
     * @param usuarioPrograma
     * 
     */
    private void auditarSolicitudesFallidos(String correo, String ip, String userAgent, String usuarioPrograma) {
        this.auditoriaServicio.createAuditoria(appName, "<NO APLICA>", null, "enviarToken", "", usuarioPrograma);
        this.auditoriaServicio.agregarParametro("correo", correo, ContantesAR00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("ip", ip, ContantesAR00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("objeto", usuarioPrograma, ContantesAR00100.DIRECCION_IN);
        this.auditoriaServicio.agregarEnvento(userAgent, ContantesAR00100.TIPO_EVENTO_SEGURIDADES);
    }

    /**
     * Metodo para validar si el social_nick y tipo.
     */
    public boolean existsBySocialNickAndTipoAndIdTokenNot(String socialNick, String tipo) {
        return tokenRepositorio.existsBySocialNickAndTipo(socialNick, tipo);
    }

    /**
     * Metodo para validar si el social_nick y tipo para un id_usuario distinto ya
     * existe.
     */
    public boolean existsBySocialNickAndTipoAndIdUsuarioNot(String socialNick, String tipo, Long idUsuario) {
        return tokenRepositorio.existsBySocialNickAndTipoAndIdUsuario(socialNick, tipo, idUsuario);
    }
}
