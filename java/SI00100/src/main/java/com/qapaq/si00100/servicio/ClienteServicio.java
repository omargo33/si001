package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.qapaq.si00100.jpa.model.Cliente;
import com.qapaq.si00100.jpa.queries.ClienteRepositorio;

/**
 * Servicio de clientes.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-02
 * 
 */
@Service
@Transactional
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    /**
     * Metodo para hacer un guardado personalizado.
     * 
     * @param cliente
     * @param usuario     
     * @param usuarioPrograma
     * 
     * @return
     */
    public Cliente saveCliente(Cliente cliente, String usuario, String usuarioPrograma) {
        cliente.setEstado("A");
        cliente.setUsuario(usuario);
        cliente.setUsuarioFecha(new Date());
        cliente.setUsuarioPrograma(usuarioPrograma);
        return clienteRepositorio.save(cliente);
    }
    
    public List<Cliente> findAllCliente(Pageable pageable) {        
        return clienteRepositorio.findAllPage(pageable);
    }

    public Cliente findClienteByIdCliente(Long id) {
        return clienteRepositorio.findAllByIdCliente(id);
    }

    public List<Cliente> findClienteByRazonSocial(String razonSocial, Pageable pageable) {
        return clienteRepositorio.findAllByRazonSocial(razonSocial, pageable);
    }

    public List<Cliente> findClienteByIdentificacion(String identificacion, Pageable pageable) {
        return clienteRepositorio.findAllByIdentificacion(identificacion, pageable);
    }

    public void deleteCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }    

    public boolean existsClienteByEmail(String email) {
        return clienteRepositorio.existsByEmail(email);
    }

    /**
     * Metodo para validar si existe un correo unico por id de cliente.
     */
    public boolean existsClienteByEmailAndIdClienteNot(String email, Long idCliente) {
        return clienteRepositorio.existsByEmailAndIdClienteNot(email, idCliente);
    }
    
    /**
     * Metodo para validar si existe un cliente unico por identificacion.
     */
    public boolean existsClienteByIdentificacion(String identificacion) {
        return clienteRepositorio.existsByIdentificacion(identificacion);
    }

    /**
     * Metodo para validar si existe una identificacion unico por id de cliente.
     */
    public boolean existsClienteByIdentificacionAndIdClienteNot(String identificacion, Long idCliente) {
        return clienteRepositorio.existsByIdentificacionAndIdCliente(identificacion, idCliente);
    }
}
