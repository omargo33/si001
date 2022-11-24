package com.qapaq.gs00100.servicio;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.Usuario;
import com.qapaq.gs00100.jpa.queries.UsuarioRepositorio;


/**
 * Objeto para dar soporte a servicio REST de usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-12
 * 
 */
@Service
@Transactional
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Metodo para crear los repositorios.
     * 
     * @param moduloRepositorio
     *
     */
    @Autowired
    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
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
        if(usuario.getIdUsuario()==null || usuario.getIdUsuario()<=0){
            usuario.setEstado("I");
            usuario.setContadorIngreso(0);
            usuario.setContadorFecha(new Date());
        }
        usuario.setUsuarioManager(StringUtils.truncate(usuarioManager, 128));        
        usuario.setUsuarioFecha(new Date());
        usuario.setUsuarioPrograma(usuarioPrograma);
        usuario.setValidador("001");
        return usuarioRepositorio.save(usuario);
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
