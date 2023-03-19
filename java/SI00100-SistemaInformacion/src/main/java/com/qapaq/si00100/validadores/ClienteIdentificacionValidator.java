package com.qapaq.si00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.qapaq.si00100.jpa.model.Cliente;
import com.qapaq.si00100.servicio.ClienteServicio;

/**
 * Clase para validar que la idenficacion no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 */

public class ClienteIdentificacionValidator implements ConstraintValidator<ClienteIdentificacion, Cliente> {
    
    @Autowired
    private ClienteServicio clienteServicio;    

    /**
     * Metodo para validar si existe un cliente con el correo.
     * 
     * @param cliente
     * @param context
     */
    @Override
    public boolean isValid(Cliente cliente, ConstraintValidatorContext context) { 
        if (cliente.getIdentificacion() == null || cliente.getIdentificacion().isEmpty()) {
            return true;
        }       

        if(clienteServicio == null) {
            return true;
        }
        
        if (cliente.getIdCliente() == null|| cliente.getIdCliente() <= 0) {            
            return !clienteServicio.existsClienteByIdentificacion(cliente.getIdentificacion());
        }

        return !clienteServicio.existsClienteByEmailAndIdClienteNot(cliente.getIdentificacion(), cliente.getIdCliente());
    }
}