package com.qapaq.si.background.consumos;

import com.qapaq.bundle.BundleFactory;
import com.qapaq.bundle.Bundles;
import com.qapaq.servicios.SolicitarRESTURL;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase para probar el consumo de un servicio REST.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-08-14
 * 
 */
@Slf4j
public class Clima extends SolicitarRESTURL {    
    private static final Bundles BUNDLE = BundleFactory.crearBundle("info");
    
    public static void main(String[] args) {
        Clima consumo = new Clima();

        consumo.setPathCertificado(null);
        consumo.setClaveCertificado(null);
        consumo.setIpProxy(null);
        consumo.setPuertoProxy(null);    
        consumo.setTimeOut(1500);
        consumo.setJsonConsulta("");
        consumo.setUrlConsulta("https://api.openweathermap.org/data/2.5/weather?lat=-2.90055&lon=-79.00453&appid=370f380af6b08548f06407b44af915d0&units=metric&lang=sp");
        if (consumo.load()){
            log.info("Respuesta: {}", consumo.getJsonRespuesta());            
        }
    }

    /**
     * Metodo para cargar el Json solicitado.
     * 
     * @return
     */ 
    public boolean load() {        
        try {
            ejecutarConsultaWebService();
        } catch (Exception e) {            
            log.info(BUNDLE.getString("error.load_01", e.getMessage()));
            return false;
        }
        if (getHttpEstado() == 200) {                        
            return true;
        }
        log.warn(BUNDLE.getString("error.load_02", getErrorRespuesta()));
        return false;
    }
}
