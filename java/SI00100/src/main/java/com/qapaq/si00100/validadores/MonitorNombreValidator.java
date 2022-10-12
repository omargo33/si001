package com.qapaq.si00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qapaq.si00100.jpa.model.Monitor;
import com.qapaq.si00100.servicio.MonitorServicio;

/**
 * Clase para validar que el nombre de la informacion no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-07
 */
@Component
public class MonitorNombreValidator implements ConstraintValidator<MonitorNombre, Monitor> {

    @Autowired
    private MonitorServicio monitorServicio;

    @Override
    public void initialize(MonitorNombre constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Metodo para validar si existe un Monitor con el nombre.
     * 
     * @param Monitor
     * @param context
     */
    @Override
    public boolean isValid(Monitor monitor, ConstraintValidatorContext context) {
        if (monitorServicio == null) {
            return true;
        }

        if (monitor.getNombre() == null || monitor.getNombre().isEmpty()) {
            return true;
        }

        if (monitor.getIdMonitor() == null || monitor.getIdMonitor() <= 0) {
            return !monitorServicio.existsMonitorByNombre(monitor.getNombre());
        }

        return !monitorServicio.existsMonitorByNombreAndIdMonitor(monitor.getNombre(), monitor.getIdMonitor());
    }
}
