package com.qapaq.lg00100.servicio;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.lg00100.jpa.model.Token;
import com.qapaq.lg00100.jpa.model.Usuario;
import com.qapaq.lg00100.jpa.queries.UsuarioRepositorio;
import com.qapaq.security.Hash;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.qapaq.ca00100.jpa.model.Direccion;
import com.qapaq.ca00100.jpa.model.Parametro;
import com.qapaq.ca00100.servicio.AuditoriaServicio;
import com.qapaq.ca00100.servicio.DireccionServicio;
import com.qapaq.ca00100.servicio.ParametroServicio;
import com.qapaq.lg00100.ConstantesLG00100;


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

    @Autowired
    private final TokenServicio tokenServicio;

    @Autowired
    private final DireccionServicio direccionServicio;

    @Autowired
    private final AuditoriaServicio auditoriaServicio;
    
    @Autowired
    private final ParametroServicio parametroServicio;

    @Getter
    private Token tokenUsuario;
    private Map<String, Parametro> mapaParametros;

    @PostConstruct
    public void init() {
        mapaParametros = parametroServicio.findByIndiceModulo(appName);
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
            usuario.setEstado(ConstantesLG00100.USUARIO_ESTADO_INACTIVO);
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
    public String validarUsuarioLogin(String userName) {
        Usuario usuario = usuarioRepositorio.findByNick(userName);
        
        if (usuario == null) {
            return ConstantesLG00100.TOKEN_ESTADO_USUARIO_NO_EXISTE;
        }

        long contadorIngreso = usuario.getContadorIngreso();
        Date contadorFecha = usuario.getContadorFecha();
        Date fechaActual = new Date();
        long intentosMaximo = mapaParametros.get(ConstantesLG00100.PARAMETRO_INTENTOS_FALLIDOS).getValorNumero01();
        long tiempoBloqueo = (60 * 60 * 1000) *  mapaParametros.get(ConstantesLG00100.PARAMETRO_TIEMPO_ESPERA).getValorNumero01();

        if (contadorIngreso >= intentosMaximo
                && fechaActual.getTime() < (contadorFecha.getTime() + tiempoBloqueo)) {
            return ConstantesLG00100.TOKEN_ESTADO_USUARIO_EXCEDE_NUMERO_INTENTOS;
        }
        tokenUsuario = tokenServicio.findBySocialNickAndTipo(userName, ConstantesLG00100.TOKEN_TIPO_CORREO);

        if(tokenUsuario==null){
            return ConstantesLG00100.TOKEN_ESTADO_USUARIO_NO_EXISTE;
        }
        
        return tokenUsuario.getEstado();
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
        try {
            auditarIngresosFallidos("login", "usuarioRechazado()", ip, userAgent, userName, usuarioPrograma);
            Usuario usuario = findByNick(userName);
            if (usuario != null) {
                usuario.setContadorIngreso(usuario.getContadorIngreso() + 1);
                usuario.setContadorFecha(new Date());
            } else {
                Direccion direccion = new Direccion();
                direccion.setElemento("usuarioRechazado()");
                direccion.setDireccionDispositivo(ip);
                direccion.setNavegadorDispositivo(userAgent);
                direccionServicio.saveDireccion(direccion, userName, usuarioPrograma);
            }
        } catch (Exception e) {
            log.error("E-GS00100-9 {}", userName);
        }
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
        this.auditoriaServicio.agregarParametro("nick", nombre, ConstantesLG00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("ip", ip, ConstantesLG00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("userAgent", userAgent, ConstantesLG00100.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("objeto", usuarioPrograma, ConstantesLG00100.DIRECCION_IN);
        this.auditoriaServicio.agregarEnvento("auditarIngresosFallidos()", ConstantesLG00100.TIPO_EVENTO_CUIDADO);
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
}
