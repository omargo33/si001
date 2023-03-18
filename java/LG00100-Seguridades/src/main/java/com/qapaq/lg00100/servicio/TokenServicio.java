package com.qapaq.lg00100.servicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.bundle.Bundles;
import com.qapaq.bundle.BundleFactory;
import com.qapaq.ca00100.ConstantesCA00100;
import com.qapaq.ca00100.servicio.AuditoriaServicio;
import com.qapaq.ca00100.servicio.NotificacionServicio;
import com.qapaq.ca00100.servicio.ParametroServicio;
import com.qapaq.lg00100.ConstantesLG00100;
import com.qapaq.lg00100.jpa.model.Token;
import com.qapaq.lg00100.jpa.queries.TokenRepositorio;
import com.qapaq.security.GeneradorClaves;
import com.qapaq.security.Hash;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de token.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-30
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TokenServicio {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.mvc.format.date-time}")
    private String formatoFecha;

    @Autowired
    private final AuditoriaServicio auditoriaServicio;
    
    @Autowired
    private final TokenRepositorio tokenRepositorio;

    @Autowired
    private final NotificacionServicio notificacionServicio;

    @Autowired
    private final ParametroServicio parametroServicio;

    private final PasswordEncoder passwordEncoder;

    private static final Bundles BUNDLES = BundleFactory.crearBundle("info");

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
        token.setEstado(ConstantesLG00100.TOKEN_ESTADO_ACTIVO);
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
    public boolean crearEnviarToken(String correo, String ip, String userAgent, String usuarioPrograma) {
        Token token = tokenRepositorio.findByCorreo(correo);
        if (token == null) {
            auditarSolicitudesFallidos(correo, ip, userAgent, usuarioPrograma);
            return false;
        }

        String password = GeneradorClaves.getPassword(GeneradorClaves.KEY_ALFANUMERICOS, 10);
        token.setEstado(ConstantesLG00100.TOKEN_ESTADO_CREADO);
        token.setTokenPassword(passwordEncoder.encode(password));
        token.setUsuarioFecha(new Date());
        token.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        tokenRepositorio.save(token);
        enviarNotificacionCrearClave(correo, password, ip, userAgent, token.getUsuario(), usuarioPrograma);
        return true;
    }

    /**
     * Metodo para enviar notificaciones de crear correo.
     * 
     * Obtiene los parametros con los indices 300 y 50
     * Crea los asuntos y contenidos
     * 
     * Agrega los parametros de hora ip y dispositivo
     * 
     * Pone en la cola de envios de correos.
     * 
     * @param correo
     * @param password
     * @param ip
     * @param userAgent
     * @param usuario
     * @param usuarioPrograma
     * 
     */
    public void enviarNotificacionCrearClave(String correo, String password, String ip, String userAgent,
            String usuario, String usuarioPrograma) {

        Long idFormato = parametroServicio.findByIdModuloAndIndice(appName, "300").getValorNumero01();
        Long idServicio = parametroServicio.findByIdModuloAndIndice(appName, "300").getValorNumero02();
        String urlSitio = parametroServicio.findByIdModuloAndIndice(appName, "50").getValorTexto01();
        
        String asunto = BUNDLES.getString("tokenServicio.enviarNotificacionCrearClave.asunto");
        String contenido = BUNDLES.getString("tokenServicio.enviarNotificacionCrearClave.cuerpo",usuario,password,urlSitio);
        Date fecha = new Date();

        SimpleDateFormat dateFormatoFecha = new SimpleDateFormat(formatoFecha);
        Map<String, String> mapaParametros = new HashMap<String, String>();
        mapaParametros.put("hora", dateFormatoFecha.format(fecha));
        mapaParametros.put("ip", ip);
        mapaParametros.put("dispositivo", userAgent);
        notificacionServicio.createNotificacion(idFormato, idServicio, asunto, contenido, correo,
                ConstantesCA00100.NOTIFICACION_ANULAR, fecha, usuario, usuarioPrograma, mapaParametros, null);
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
        this.auditoriaServicio.agregarParametro("correo", correo, ConstantesLG00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("ip", ip, ConstantesLG00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("objeto", usuarioPrograma, ConstantesLG00100.DIRECCION_IN);
        this.auditoriaServicio.agregarEnvento(userAgent, ConstantesLG00100.TIPO_EVENTO_SEGURIDADES);
    }

    /**
     * Metodo para validar si un usuario existe.
     */
    public boolean validarUsuario(String correo, String tipo) {
        return tokenRepositorio.existsBySocialNickAndTipo(correo, tipo);
    }
}
