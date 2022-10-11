package com.qapaq.si00100.http.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.Cliente;
import com.qapaq.si00100.servicio.ClienteServicio;

/**
 * Clase para controlar las peticiones de los clientes.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteControlador extends ComonControlador {
    private ClienteServicio clienteServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    /**
     * Constructor de la clase.
     * 
     * @param clienteServicio
     */
    @Autowired
    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    /**
     * Metodo para obtener todos los clientes.
     * 
     * @return
     */
    @GetMapping("/")
    public List<Cliente> getAllCliente(Pageable pageable) {
        return clienteServicio.findAllCliente(pageable);
    }

    /**
     * Metodo para obtener un cliente por idCliente.
     * 
     */
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteServicio.findClienteByIdCliente(id);
    }

    /**
     * Metodo para obtener clientes por razon social.
     * 
     * @param razonSocial
     * @return
     */
    @GetMapping("/razonSocial={razonSocial}")
    public List<Cliente> getClienteByRazonSocial(@PathVariable(name = "razonSocial") String razonSocial,
            Pageable pageable) {
        return clienteServicio.findClienteByRazonSocial(razonSocial, pageable);
    }

    /**
     * Metodo para obtener clientes por identificacion.
     * 
     * @param razonSocial
     * @return
     */
    @GetMapping("/identificacion={identificacion}")
    public List<Cliente> getClienteByIdentificacion(@PathVariable(name = "identificacion") String identificacion,
            Pageable pageable) {
        return clienteServicio.findClienteByIdentificacion(identificacion, pageable);
    }

    /**
     * Metodo para crear clientes.
     * 
     * @param cliente
     * 
     */
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
        return clienteServicio.saveCliente(cliente, "cambiar()", appName + " " + appVersion);
    }

    /**
     * Metodo para actualizar clientes.
     * 
     * @param cliente
     */
    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Cliente actualizarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteServicio.saveCliente(cliente, "cambiar()", appName + " " + appVersion);
    }

    /**
     * Metodo para eliminar clientes.
     * 
     * @param idCliente
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) throws ForeignKeyException{
        clienteServicio.deleteCliente(id);
    }
}