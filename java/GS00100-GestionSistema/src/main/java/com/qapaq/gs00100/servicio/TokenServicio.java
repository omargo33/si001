package com.qapaq.gs00100.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
import com.qapaq.bundle.BundleFactory;
import com.qapaq.bundle.Bundles;
 */
import com.qapaq.gs00100.jpa.model.Token;
import com.qapaq.gs00100.jpa.pojo.UsuarioClave;
import com.qapaq.gs00100.jpa.queries.TokenRepositorio;

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

    //TODO cambio por auditoria
    /*
    @Autowired
    private final AuditoriaServicioCat auditoriaServicioCat;
 */

    @Autowired
    private final TokenRepositorio tokenRepositorio;


    //TODO cambio por auditoria
    /*

    @Autowired
    private final ParametroServicioCat parametroServicioCat;

    @Autowired
    private final NotificacionServicioCat notificacionServicioCat;
*/
    //private static final Bundles BUNDLES = BundleFactory.crearBundle("info");

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
        //TODO cambio de parametros
        /* 
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = GeneradorClaves.getPassword(GeneradorClaves.KEY_ALFANUMERICOS, 10);

        token.setTokenPassword(passwordEncoder.encode(password));
        token.setEstado(ConstantesCA00100.TOKEN_ESTADO_ACTIVO);
        token.setValidador(
                Hash.crearHash(String.valueOf(token.getIdUsuario()), token.getTipo(), token.getSocialNick()));
        token.setUsuario(StringUtils.truncate(usuario, 128));
        token.setUsuarioFecha(new Date());
        token.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));

        return tokenRepositorio.save(token);
        */
        return null;
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
    public boolean enviarToken(String nick, String usuario, String ip, String userAgent, String usuarioPrograma) {
        //TODO cambio para parametros
        /*
        Token token = tokenRepositorio.findBySocialNickAndTipo(nick, ConstantesCA00100.TOKEN_TIPO_CORREO);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (token == null) {
            auditarSolicitudesFallidos(nick, usuario, ip, userAgent, usuarioPrograma);
            return false;
        }

        String password = GeneradorClaves.getPassword(GeneradorClaves.KEY_ALFANUMERICOS, 10);
        //TODO cambio para parametros
        //token.setEstado(ConstantesCA00100.TOKEN_ESTADO_CREADO);
        token.setTokenPassword(passwordEncoder.encode(password));
        token.setUsuario(usuario);
        token.setUsuarioFecha(new Date());
        token.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        tokenRepositorio.save(token);

        enviarNotificacionCrearClave(token.getCorreo(), password, ip, userAgent, nick, usuarioPrograma);
         */
        return true;
    }

    /**
     * Metodo para cambiar la clave con validaciones diversas y una vez terminado
     * envia un correo sobre el cambio.
     * 
     * 
     * @param datosClave
     * @return
     */
    public boolean cambiarToken(UsuarioClave datosClave) {
        //todo cambio por parametros
        /*
        Token token = tokenRepositorio.findBySocialNickAndTipo(datosClave.getSocialNick(),
                ConstantesCA00100.TOKEN_TIPO_CORREO);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (token == null) {
            auditarSolicitudesFallidos(datosClave.getSocialNick(), datosClave.getSocialNick(), datosClave.getIp(),
                    datosClave.getUserAgent(),
                    datosClave.getUsuarioPrograma());
            return false;
        }

        if (passwordEncoder.matches(datosClave.getClaveActual(), token.getTokenPassword())) {
            return false;
        }
        token.setEstado(ConstantesCA00100.TOKEN_ESTADO_CREADO);
        token.setTokenPassword(passwordEncoder.encode(datosClave.getClaveNueva()));
        token.setUsuarioFecha(new Date());
        token.setUsuario(datosClave.getSocialNick());
        token.setUsuarioPrograma(StringUtils.truncate(datosClave.getUsuarioPrograma(), 256));
        tokenRepositorio.save(token);

        enviarNotificacionCambiarClave(token.getCorreo(), datosClave.getIp(), datosClave.getUserAgent(),
                datosClave.getSocialNick(), datosClave.getUsuarioPrograma());
         */
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
    private void auditarSolicitudesFallidos(String nick, String usuario, String ip, String userAgent,
            String usuarioPrograma) {
        
        //TODO cambio de auditoria
        /*
        auditoriaServicioCat.createAuditoria(appName, "<NO APLICA>", null, "enviarToken", usuario, usuarioPrograma);
        auditoriaServicioCat.agregarParametro("nick", nick, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("ip", ip, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("objeto", usuarioPrograma,
                ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarEnvento(userAgent, ConstantesCA00100.AUDITORIA_EVENTO_TIPO_SEGURIDADES);
     */
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
    private void enviarNotificacionCrearClave(String correo, String password, String ip, String userAgent,
            String usuario, String usuarioPrograma) {

        //TODO cambio por parametros
        
        /*

        Long idFormato = parametroServicioCat.findByIdModuloAndIndice(appName, "300").getValorNumero01();
        Long idServicio = parametroServicioCat.findByIdModuloAndIndice(appName, "300").getValorNumero02();
        String urlSitio = parametroServicioCat.findByIdModuloAndIndice(appName, "50").getValorTexto01();

        String asunto = BUNDLES.getString("tokenServicio.enviarNotificacionCrearClave.asunto");
        String contenido = BUNDLES.getString("tokenServicio.enviarNotificacionCrearClave.cuerpo", usuario, password,
                urlSitio);
        Date fecha = new Date();

        SimpleDateFormat dateFormatoFecha = new SimpleDateFormat(formatoFecha);
        Map<String, String> mapaParametros = new HashMap<String, String>();
        mapaParametros.put("hora", dateFormatoFecha.format(fecha));
        mapaParametros.put("ip", ip);
        mapaParametros.put("dispositivo", userAgent);
        notificacionServicioCat.createNotificacion(idFormato, idServicio, asunto, contenido, correo,
                ConstantesCA00100.NOTIFICACION_ANULAR, fecha, usuario, usuarioPrograma, mapaParametros, null);
     */
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
    private void enviarNotificacionCambiarClave(String correo, String ip, String userAgent,
            String usuario, String usuarioPrograma) {

                //todo cambio de parametros
                /*

        Long idFormato = parametroServicioCat.findByIdModuloAndIndice(appName, "300").getValorNumero01();
        Long idServicio = parametroServicioCat.findByIdModuloAndIndice(appName, "300").getValorNumero02();
        String urlSitio = parametroServicioCat.findByIdModuloAndIndice(appName, "50").getValorTexto01();

        String asunto = BUNDLES.getString("tokenServicio.enviarNotificacionCrearClave.asunto");
        String contenido = BUNDLES.getString("tokenServicio.enviarNotificacionCrearClave.cuerpo", usuario, urlSitio);
        Date fecha = new Date();

        SimpleDateFormat dateFormatoFecha = new SimpleDateFormat(formatoFecha);
        Map<String, String> mapaParametros = new HashMap<String, String>();
        mapaParametros.put("hora", dateFormatoFecha.format(fecha));
        mapaParametros.put("ip", ip);
        mapaParametros.put("dispositivo", userAgent);
        notificacionServicioCat.createNotificacion(idFormato, idServicio, asunto, contenido, correo,
                ConstantesCA00100.NOTIFICACION_ANULAR, fecha, usuario, usuarioPrograma, mapaParametros, null);
     */
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