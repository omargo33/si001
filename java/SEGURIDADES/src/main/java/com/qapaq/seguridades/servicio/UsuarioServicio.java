package com.qapaq.seguridades.servicio;

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

import com.qapaq.catalogos.jpa.model.Parametro;
import com.qapaq.catalogos.servicio.ParametroServicio;
import com.qapaq.seguridades.jpa.model.Direccion;
import com.qapaq.seguridades.jpa.model.Token;
import com.qapaq.seguridades.jpa.model.Usuario;
import com.qapaq.seguridades.jpa.queries.UsuarioRepositorio;
import com.qapaq.security.Hash;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import com.qapaq.seguridades.ConstantesSeguridades;


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
public class UsuarioServicio {

    @Value("${app.name}")
    private String appName;

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
            usuario.setEstado(ConstantesSeguridades.USUARIO_ESTADO_INACTIVO);
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
            return ConstantesSeguridades.TOKEN_ESTADO_USUARIO_NO_EXISTE;
        }

        long contadorIngreso = usuario.getContadorIngreso();
        Date contadorFecha = usuario.getContadorFecha();
        Date fechaActual = new Date();
        long intentosMaximo = mapaParametros.get(ConstantesSeguridades.PARAMETRO_INTENTOS_FALLIDOS).getValorNumero01();
        long tiempoBloqueo = (60 * 60 * 1000) *  mapaParametros.get(ConstantesSeguridades.PARAMETRO_TIEMPO_ESPERA).getValorNumero01();

        if (contadorIngreso >= intentosMaximo
                && fechaActual.getTime() < (contadorFecha.getTime() + tiempoBloqueo)) {
            return ConstantesSeguridades.TOKEN_ESTADO_USUARIO_EXCEDE_NUMERO_INTENTOS;
        }
        tokenUsuario = tokenServicio.findBySocialNickAndTipo(userName, ConstantesSeguridades.TOKEN_TIPO_CORREO);

        if(tokenUsuario==null){
            return ConstantesSeguridades.TOKEN_ESTADO_USUARIO_NO_EXISTE;
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
        this.auditoriaServicio.agregarParametro("nick", nombre, ConstantesSeguridades.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("ip", ip, ConstantesSeguridades.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("userAgent", userAgent, ConstantesSeguridades.DIRECCION_IN);
        this.auditoriaServicio.agregarParametro("objeto", usuarioPrograma, ConstantesSeguridades.DIRECCION_IN);
        this.auditoriaServicio.agregarEnvento("auditarIngresosFallidos()", ConstantesSeguridades.TIPO_EVENTO_CUIDADO);
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
