package com.qapaq.gs00101.servicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qapaq.gs00101.jpa.model.CodigoDefinidoUsuario;
import com.qapaq.gs00101.jpa.queries.CodigoDefinidoUsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CodigoDefinidoUsuarioServicio {
    
    @Autowired
    private final CodigoDefinidoUsuarioRepositorio codigoDefinidoUsuarioRepositorio;

    /**
     * Metodo para buscar todos los datos del repositorio.
     * 
     * @param id
     * @return
     */
    public List<CodigoDefinidoUsuario> findAll() {
        return codigoDefinidoUsuarioRepositorio.findAll();
    }
}
