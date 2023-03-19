package com.qapaq.si00100.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.qapaq.si00100.jpa.model.Cliente;
import com.qapaq.si00100.servicio.ClienteServicio;

/**
 * Clase para validar que el correo no exista.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 */
public class ClienteCorreoValidator implements ConstraintValidator<ClienteCorreo, Cliente> {
    
    @Autowired
    private ClienteServicio clienteServicio;    

    @Override
    public void initialize(ClienteCorreo constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Metodo para validar si existe un cliente con el correo.
     * 
     * @param cliente
     * @param context
     */
    @Override
    public boolean isValid(Cliente cliente, ConstraintValidatorContext context) { 
        if (cliente.getCorreo() == null || cliente.getCorreo().isEmpty()) {
            return true;
        }       

        if(clienteServicio == null) {
            return true;
        }
        
        if (cliente.getIdCliente() == null|| cliente.getIdCliente() <= 0) {            
            return !clienteServicio.existsClienteByEmail(cliente.getCorreo());
        }

        return !clienteServicio.existsClienteByEmailAndIdClienteNot(cliente.getCorreo(), cliente.getIdCliente());
    }
}