package com.qapaq.gs00100.jpa.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qapaq.gs00100.validadores.UsuarioCambioClave;

import lombok.Data;

/**
 * Metodo para metodo de cambio de clave en el servicio de usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-04-02
 * 
 */
@Data
@UsuarioCambioClave(titulo = "clave", message = "E-GS00100-4")
@JsonIgnoreProperties({"ip", "userAgent"})
public class UsuarioClave {

    @NotNull(message = "E-GS00100-2")
    @NotBlank(message = "E-GS00100-3")
    private String socialNick;
    
    @NotNull(message = "E-GS00100-2")
    @NotBlank(message = "E-GS00100-3")
    private String claveActual;
    
    @NotNull(message = "E-GS00100-2")
    @NotBlank(message = "E-GS00100-3")
    private String claveNueva;
    
    @NotNull(message = "E-GS00100-2")
    @NotBlank(message = "E-GS00100-3")
    private String claveNuevaConfirmacion;

    private String ip;
    private String userAgent;
    private String usuarioPrograma;
}
