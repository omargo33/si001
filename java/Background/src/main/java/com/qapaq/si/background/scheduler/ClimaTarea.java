package com.qapaq.si.background.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.qapaq.si.background.jpa.model.Ciudad;
import com.qapaq.si.background.servicio.CiudadServicio;

/**
 * Objeto para la ejecución de la tarea de clima.
 * 
 * @author o.velez@qapaq.io
 * @date: 2022/08/14
 * 
 */
public class ClimaTarea {
    private static final long ONE_HOUR = 1 * 60 * 60 * 1000;

    @Autowired
    private CiudadServicio ciudadServicio;

    //TODO cambio de parametros api
    /*
    @Autowired
    private ParametroServicio parametroServicio;
 */
    @Scheduled(fixedDelay = ONE_HOUR)
    public void ejecutar() {
        buscarClimaPorCiudades();
    }

    /**
     * Metodo para ejecutar la tarea de clima.
     * 
     * Obtiene parametros de configuracion y ejecuta la tarea de clima para cada
     * ciudad.
     * 
     * Luego crea una nueva entrada al clima, se parametriza Usuario,
     * usuario_programa y pone la usuario_fecha de la fecha actual.
     * 
     */
    private void buscarClimaPorCiudades() {
        // String usuario =
        // parametroServicio.buscarParametro("indice003").getValorTexto01();

        List<Ciudad> ciudades = ciudadServicio.listarCiudades();

        for (Ciudad c : ciudades) {
            c.getLatitud();
            c.getLongitud();

        }
    }
}