package com.qapaq.http.request.Common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Interface para controlar las peticiones de las ciudades.
 * 
 * @author o.velez@qapaq.io
 * @since 2020-11-23
 * 
 */
public interface TokenRefreshControlador {

    public boolean isActiveUser(String username);

    public List<String> getRoles(String username);

    public void refresh(HttpServletRequest request, HttpServletResponse response);

    public void lostPassword(HttpServletRequest request, HttpServletResponse response);
    
}
