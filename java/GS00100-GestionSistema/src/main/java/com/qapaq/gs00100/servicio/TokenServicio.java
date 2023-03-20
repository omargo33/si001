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

import com.qapaq.ca00100.ConstantesCA00100;
import com.qapaq.ca00100.servicio.AuditoriaServicioCat;
import com.qapaq.gs00100.jpa.model.Token;
import com.qapaq.gs00100.jpa.queries.TokenRepositorio;
import com.qapaq.security.GeneradorClaves;
import com.qapaq.security.Hash;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Objeto para dar soporte a servicio REST de token.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-30
 * 
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TokenServicio {

    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private final AuditoriaServicioCat auditoriaServicioCat;
    @Autowired
    private final TokenRepositorio tokenRepositorio;
    @Autowired
    private final ParametroServicio parametroServicio;
    @Autowired

    private final PasswordEncoder passwordEncoder;

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
        token.setEstado(ConstantesCA00100.TOKEN_ESTADO_ACTIVO);
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
        token.setEstado(ConstantesCA00100.TOKEN_ESTADO_CREADO);
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
        auditoriaServicioCat.createAuditoria(appName, "<NO APLICA>", null, "enviarToken", "", usuarioPrograma);
        auditoriaServicioCat.agregarParametro("correo", correo, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("ip", ip, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("objeto", usuarioPrograma, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarEnvento(userAgent, ConstantesCA00100.AUDITORIA_EVENTO_TIPO_SEGURIDADES);
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