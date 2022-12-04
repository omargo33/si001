package com.qapaq.bundle;

/**
 * Clase para crear el bundle.
 *
 * @author o.velez@qapaq.io
 * @created 2022-07-05
 */
public class BundleFactory{

    /**
     * Constructor de la clase.
     */
    private BundleFactory() {
        super();
    }
    
    /**
     * Crea el bundle para instancias.
     * 
     * @param bundlePath
     * @return
     */
    public static Bundles crearBundle(String bundlePath) {
        Bundles bundles = new Bundles();
        bundles.setBundle(bundlePath);
        return bundles;
    }
}