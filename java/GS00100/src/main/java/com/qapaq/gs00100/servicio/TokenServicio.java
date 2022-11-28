package com.qapaq.gs00100.servicio;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.Token;
import com.qapaq.gs00100.jpa.queries.TokenRepositorio;


/**
 * Objeto para dar soporte a servicio REST de token.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-30
 * 
 */
@Service
@Transactional("gs001001TransactionManager")
public class TokenServicio {

    private final TokenRepositorio tokenRepositorio;

    /**
     * Metodo para crear los repositorios.
     * 
     * @param moduloRepositorio
     *
     */
    @Autowired
    public TokenServicio(TokenRepositorio tokenRepositorio) {
        this.tokenRepositorio = tokenRepositorio;
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
        token.setEstado(StringUtils.truncate("A", 8));
        token.setValidador(StringUtils.truncate("001", 512));
        token.setUsuario(StringUtils.truncate(usuario, 128));
        token.setUsuarioFecha(new Date());
        token.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));        
        return tokenRepositorio.save(token);
    }

    /**
     * Metodo para borrar un token de manera logico.
     * @param idToken
     */
    public void deleteByIdToken(Long idToken) {
        tokenRepositorio.deleteByIdToken(idToken);
    }    

    /**
     * Metodo para buscar un token por socialNick y tipo.
     * 
     * @param socialNick
     * @param tipo     * 
     */
    public Token findBySocialNickAndTipo(String socialNick, String tipo) {
        return tokenRepositorio.findBySocialNickAndTipo(socialNick, tipo);
    }

    
    /**
     * Metodo para validar si el social_nick y tipo.
     */
    public boolean existsBySocialNickAndTipoAndIdTokenNot(String socialNick, String tipo) {
        return tokenRepositorio.existsBySocialNickAndTipo(socialNick, tipo);
    }

    /**
     * Metodo para validar si el social_nick y tipo para un id_usuario distinto ya existe.
     */
    public boolean existsBySocialNickAndTipoAndIdUsuarioNot(String socialNick, String tipo, Long idUsuario) {
        return tokenRepositorio.existsBySocialNickAndTipoAndIdUsuario(socialNick, tipo, idUsuario);
    }
}
