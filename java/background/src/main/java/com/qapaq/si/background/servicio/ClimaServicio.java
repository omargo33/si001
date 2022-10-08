package com.qapaq.si.background.servicio;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import com.qapaq.si.background.jpa.model.Ciudad;
import com.qapaq.si.background.jpa.model.Clima;
import com.qapaq.si.background.jpa.queries.ClimaRepositorio;

@Service
@Transactional
public class ClimaServicio {

    @Value("${aplicacion.nombre}")
    String aplicacion;

    @Value("${aplicacion.version}")
    String version;

    private final ClimaRepositorio climaRepositorio;

    public ClimaServicio(ClimaRepositorio climaRepositorio) {
        this.climaRepositorio = climaRepositorio;
    }

    /**
     * Metodo para crear una nueva entrada al clima, se parametriza Usuario,
     * usuario_programa y pone la usuario_fecha de la fecha actual.
     * 
     * @param ciudad
     * @param json
     * @param usuario
     */
    public void crearClima(Ciudad ciudad, String json, String usuario) {
        Clima clima = new Clima();        
        clima.setIdCiudad(ciudad.getIdCiudad());        
        clima.setUsuario(usuario);
        clima.setJson(json);
        clima.setUsuarioPrograma(aplicacion + "-" + version);
        clima.setUsuarioFecha(new java.util.Date());
        climaRepositorio.save(clima);
    }
}