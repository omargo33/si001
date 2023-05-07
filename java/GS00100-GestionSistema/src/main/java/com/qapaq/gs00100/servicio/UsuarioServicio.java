package com.qapaq.gs00100.servicio;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.Parametro;
import com.qapaq.gs00100.jpa.model.Usuario;
import com.qapaq.gs00100.jpa.queries.UsuarioRepositorio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.qapaq.gs00100.jpa.model.Token;

/**
 * Objeto para dar soporte a servicio REST de usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-12
 * 
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServicio {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    //TODO: cambio por auditoria
    /*
    @Autowired
    private final TokenServicio tokenServicio;
    
    
    @Autowired
    private final DireccionServicioCat direccionServicioCat;
    
    @Autowired
    private final AuditoriaServicioCat auditoriaServicioCat;
     */
    @Autowired
    private final ParametroServicio parametroServicio;

    @Getter
    private Token tokenUsuario;
    private Map<String, Parametro> mapaParametros;


    @PostConstruct
    public void init() {
        log.info(":p UsuarioServicio.init() {}", appName);
        
        //mapaParametros = parametroServicio.findByIndiceModulo(appName);
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
       //TODO

       /*
       
        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() <= 0) {
            usuario.setEstado(ConstantesCA00100.USUARIO_ESTADO_INACTIVO);
            usuario.setContadorIngreso(0);
            usuario.setContadorFecha(new Date());
        }
        usuario.setUsuarioManager(StringUtils.truncate(usuarioManager, 128));
        usuario.setUsuarioFecha(new Date());
        usuario.setUsuarioPrograma(usuarioPrograma);
        usuario.setValidador(Hash.crearHash(usuario.getNick()));
        return usuarioRepositorio.save(usuario);
         */
        return null;
    }

    /**
     * Metodo para validar un usuario al ingreso.
     * 
     * @param userName
     * @param elemento
     */
    public String validarUsuarioLogin(String userName) {
        //TODO cambio por auditoria
        /*
        Usuario usuario = usuarioRepositorio.findByNick(userName);
        
        if (usuario == null) {
            return ConstantesCA00100.TOKEN_ESTADO_USUARIO_NO_EXISTE;
        }

        long contadorIngreso = usuario.getContadorIngreso();
        Date contadorFecha = usuario.getContadorFecha();
        Date fechaActual = new Date();
        long intentosMaximo = mapaParametros.get(ConstantesCA00100.PARAMETRO_INTENTOS_FALLIDOS).getValorNumero01();
        long tiempoBloqueo = (60 * 60 * 1000) *  mapaParametros.get(ConstantesCA00100.PARAMETRO_TIEMPO_ESPERA).getValorNumero01();

        if (contadorIngreso >= intentosMaximo
                && fechaActual.getTime() < (contadorFecha.getTime() + tiempoBloqueo)) {
            return ConstantesCA00100.TOKEN_ESTADO_USUARIO_EXCEDE_NUMERO_INTENTOS;
        }
        tokenUsuario = tokenServicio.findBySocialNickAndTipo(userName, ConstantesGS00100.TIPO_USER_NAME);

        if(tokenUsuario==null){
            return ConstantesCA00100.TOKEN_ESTADO_USUARIO_NO_EXISTE;
        }
        
        return tokenUsuario.getEstado();
         */
        return null;
    }

    /**
     * Metodo para con procesos relativos a usuarios rechazados en la plataforma.
     * 
     * Audita ingresos fallidos
     * Actualiza contador de ingresos fallidos
     * De no existir el usuario, agrega un registro de direcciones de ingresos
     * fallidos
     * 
     * @param ip
     * @param userAgent
     * @param userName
     * @param usuarioPrograma
     */
    public void usuarioRechazado(String ip, String userAgent, String userName, String usuarioPrograma) {
        //TODO cambio por auditoria
        /*
        try {
            auditarIngresosFallidos("login", "usuarioRechazado()", ip, userAgent, userName, usuarioPrograma);
            Usuario usuario = findByNick(userName);
            if (usuario != null) {
                usuario.setContadorIngreso(usuario.getContadorIngreso() + 1);
                usuario.setContadorFecha(new Date());
            } else {
                DireccionCat direccionCat = new DireccionCat();
                direccionCat.setElemento("usuarioRechazado()");
                direccionCat.setDireccionDispositivo(ip);
                direccionCat.setNavegadorDispositivo(userAgent);
                direccionServicioCat.saveDireccion(direccionCat, userName, usuarioPrograma);
            }
        } catch (Exception e) {
            log.error("E-GS00100-9 {}", userName);
        }
         */
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

        //TODO cambio por auditoria
        /*         
        auditoriaServicioCat.createAuditoria(nombre, "<NO APLICA>", null, elemento, usuario, usuarioPrograma);
        auditoriaServicioCat.agregarParametro("nick", nombre, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("ip", ip, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("userAgent", userAgent, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarParametro("objeto", usuarioPrograma, ConstantesCA00100.AUDITORIA_PARAMETRO_DIRECCION_IN);
        auditoriaServicioCat.agregarEnvento("auditarIngresosFallidos()", ConstantesCA00100.AUDITORIA_EVENTO_TIPO_CUIDADO);
        */        
    }

    /**
     * Metodo para inicializar los contadores de ingreso.
     * 
     * @param userName
     */
    public void inicialiarContadoresIngreso(String userName) {
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
