package com.qapaq.gs00100.servicio;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapaq.ar00100.servicio.AuditoriaServicio;
import com.qapaq.ar00100.servicio.DireccionServicio;
import com.qapaq.gs00100.ConstantesGS00100;
import com.qapaq.gs00100.jpa.model.Parametro;
import com.qapaq.gs00100.jpa.model.Token;
import com.qapaq.gs00100.jpa.model.Usuario;
import com.qapaq.gs00100.jpa.queries.UsuarioRepositorio;
import com.qapaq.security.Hash;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import com.qapaq.ar00100.ContantesAR00100;
import com.qapaq.ar00100.jpa.model.Direccion;

/**
 * Objeto para dar soporte a servicio REST de usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-12
 * 
 */
@Slf4j
@Service
@Transactional("gs001001TransactionManager")
public class UsuarioServicio {

    @Value("${app.name.loging}")
    private String appNameLogin;

    public static final int USUARIO_NO_EXISTE = -1;
    public static final int USUARIO_EXCEDE_NUMERO_INTENTOS = -2;
    public static final int USUARIO_NO_ACTIVO = -3;
    public static final int USUARIO_OK = 0;

    private UsuarioRepositorio usuarioRepositorio;

    private final TokenServicio tokenServicio;
    private final DireccionServicio direccionServicio;
    private final AuditoriaServicio auditoriaServicio;
    private final ParametroServicio parametroServicio;

    @Getter
    private Token tokenUsuario;
    private Map<String, Parametro> mapaParametros;


    /**
     * Metodo para crear los repositorios.
     * 
     * @param moduloRepositorio
     *
     */
    @Autowired
    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio, TokenServicio tokenServicio,
            DireccionServicio direccionServicio, AuditoriaServicio auditoriaServicio,
            ParametroServicio parametroServicio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.tokenServicio = tokenServicio;
        this.direccionServicio = direccionServicio;
        this.auditoriaServicio = auditoriaServicio;
        this.parametroServicio = parametroServicio;
        
    }

    @PostConstruct
    public void init() {        
        mapaParametros = parametroServicio.findByIndiceModulo(appNameLogin);
    }

    /**
     * Metodo para mostrar todos los usuarios de forma paginada.
     *
     * @return
     */
    public List<Usuario> findAllUsuarios(Pageable pageable) {
        return usuarioRepositorio.findAllByEstadoNot(pageable);
    }

    /**
     * Metodo para buscar por idUsuario.
     * 
     */
    public Usuario findByIdUsuario(Long idUsuario) {
        return usuarioRepositorio.findByIdUsuario(idUsuario);
    }

    /**
     * Metodo para buscar por nick.
     * 
     */
    public Usuario findByNick(String nick) {
        return usuarioRepositorio.findByNick(nick);
    }

    /**
     * Metodo para borrar un usuario de manera logico.
     * 
     * @param idUsuario
     */
    public void deleteUsuario(Long idUsuario) {
        usuarioRepositorio.deleteByIdUsuario(idUsuario);
    }

    /**
     * Metodo para crear/actualizar un usuario.
     * 
     * @param usuario
     * @return
     */
    public Usuario guardarUsuario(Usuario usuario, String usuarioManager, String usuarioPrograma) {
        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() <= 0) {
            usuario.setEstado(ConstantesGS00100.USUARIO_ESTADO_INACTIVO);
            usuario.setContadorIngreso(0);
            usuario.setContadorFecha(new Date());
        }
        usuario.setUsuarioManager(StringUtils.truncate(usuarioManager, 128));
        usuario.setUsuarioFecha(new Date());
        usuario.setUsuarioPrograma(usuarioPrograma);
        usuario.setValidador(Hash.crearHash(usuario.getNick()));
        return usuarioRepositorio.save(usuario);
    }

    /**
     * Metodo para validar un usuario al ingreso.
     * 
     * @param userName
     * @param elemento
     */
    public int validarUsuarioLogin(String userName) {
        Usuario usuario = usuarioRepositorio.findByNick(userName);

        if (usuario != null) {
            long contadorIngreso = usuario.getContadorIngreso();
            Date contadorFecha = usuario.getContadorFecha();
            Date fechaActual = new Date();
            long intentosMaximo = mapaParametros.get(ConstantesGS00100.PARAMETRO_INTENTOS_FALLIDOS).getValorNumero01();
            long tiempoBloqueo = mapaParametros.get(ConstantesGS00100.PARAMETRO_TIEMPO_ESPERA).getValorNumero01() *
                    (60 * 60 * 1000);

            if (contadorIngreso >= intentosMaximo
                    && (contadorFecha.getTime() + tiempoBloqueo) < fechaActual.getTime()) {
                return USUARIO_EXCEDE_NUMERO_INTENTOS;
            } else {
                tokenUsuario = tokenServicio.findBySocialNickAndTipo(userName, ConstantesGS00100.TIPO_USER_NAME);
                if (tokenUsuario.getEstado().compareTo(ConstantesGS00100.TOKEN_ESTADO_ACTIVO) != 0) {
                    return USUARIO_NO_ACTIVO;
                }
                return USUARIO_OK;
            }
        }
        return USUARIO_NO_EXISTE;
    }

    public String usuarioRechazado(String ip, String userAgent, String userName, String usuarioPrograma) {
        String json = "";
        Map<String, String> error = new HashMap<>();

        auditarIngresosFallidos("login", "usuarioRechazado()", ip, userAgent, userName, usuarioPrograma);

        Usuario usuario = findByNick(userName);
        if (usuario != null) {       
            usuario.setContadorIngreso(usuario.getContadorIngreso() + 1);
            usuario.setContadorFecha(new Date());
        }else{
            Direccion direccion = new Direccion();
            direccion.setElemento("usuarioRechazado()");
            direccion.setDireccionDispositivo(ip);
            direccion.setNavegadorDispositivo(userAgent);
            direccionServicio.saveDireccion(direccion, userName, usuarioPrograma);
        }

        error.put("error", "E-GS00100-9");
        try {
            new ObjectMapper().writeValueAsString(error);
        } catch (Exception e) {
            log.error("E-GS00100-9 {}", userName);
        }
        return json;
    }

    /**
     * Metodo para auditar el error de ingreso.
     * 
     * @param nombre
     * @param valor01
     * @param valor02
     * @param elemento
     * @param ip
     * @param usuario
     * @param usuarioPrograma
     */
    private void auditarIngresosFallidos(String nombre, String elemento, String ip,
            String userAgent,
            String usuario,
            String usuarioPrograma) {

        this.auditoriaServicio.createAuditoria(nombre, "<NO APLICA>", null, elemento, usuario, usuarioPrograma);
        this.auditoriaServicio.agregarParametro("nick", nombre, ContantesAR00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("ip", ip, ContantesAR00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("userAgent", userAgent, ContantesAR00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("objeto", usuarioPrograma, ContantesAR00100.DIRECCION_IN);

        this.auditoriaServicio.agregarEnvento("auditarIngresosFallidos()", ContantesAR00100.TIPO_EVENTO_CUIDADO);
    }

    /**
     * Metodo para inicializar los contadores de ingreso.
     * 
     * @param userName
     */
    public void inicialiarContadoresIngreso(String userName){
        Usuario usuario = findByNick(userName);
        if (usuario != null) {            
            usuario.setContadorIngreso(0);
            usuario.setContadorFecha(new Date());
        }
    }

    /**
     * Metodo para buscar por nombre o apellido.
     * 
     */
    public List<Usuario> findByNombreApellido(String nombreApellido, Pageable pageable) {
        return usuarioRepositorio.findByNombreApellido(nombreApellido, pageable);
    }

    /**
     * Metodo para validar nick.
     * 
     */
    public boolean isNickUnico(String nick) {
        return usuarioRepositorio.existsByNick(nick);
    }

    /**
     * Metodo para validar nick diferente idUsuario.
     * 
     */
    public boolean isNickUnico(String nick, Long idUsuario) {
        return usuarioRepositorio.existsByNickAndIdUsuarioNot(nick, idUsuario);
    }
}
