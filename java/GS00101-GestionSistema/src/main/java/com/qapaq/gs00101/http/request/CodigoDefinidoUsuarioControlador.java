package com.qapaq.gs00101.http.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.gs00101.servicio.CodigoDefinidoUsuarioServicio;

@RestController
@RequestMapping(value = "/v1/codigosdefinidosusuarios")
public class CodigoDefinidoUsuarioControlador {

    @Autowired
    private CodigoDefinidoUsuarioServicio codigoDefinidoUsuarioServicio;

    /**
     * Metodo para mostrar todos los CodigosDefinidosUsuarios.
     */
    @GetMapping("/")
    public String getAllCodigosDefinidosUsuarios() {
        return codigoDefinidoUsuarioServicio.findAll();
    }

}
