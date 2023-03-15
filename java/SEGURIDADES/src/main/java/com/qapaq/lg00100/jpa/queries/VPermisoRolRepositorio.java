package com.qapaq.lg00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.lg00100.jpa.model.VPermisoRol;

/**
 * Interface para la clase VPermiso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-12
 * 
 */
public interface VPermisoRolRepositorio extends JpaRepository <VPermisoRol, Long>{

    /**
     * Metodo para buscar por nick y indice_modulo
     * 
     */
    @Query(value =  "select ((xx.id_menu*100) + xx.id_rol) as id_permiso_rol, ( select task_flow from menu where id_menu = xx.id_menu) as url, ( select tipo from rol where id_rol = xx.id_rol) as rol, ( select count(*) from permiso p2 where p2.id_menu = xx.id_menu and p2.id_rol = xx.id_rol and p2.crear = 'S' ) as crear, ( select count(*) from permiso p2 where p2.id_menu = xx.id_menu and p2.id_rol = xx.id_rol and p2.actualizar = 'S' ) as actualizar, ( select count(*) from permiso p2 where p2.id_menu = xx.id_menu and p2.id_rol = xx.id_rol and p2.borrar = 'S' ) as borrar, ( select count(*) from permiso p2 where p2.id_menu = xx.id_menu and p2.id_rol = xx.id_rol and p2.ver_auditoria = 'S' ) as ver_auditoria from ( select p.id_menu, p.id_rol from permiso p, ( select m.id_menu from menu m where m.id_modulo =( select id_modulo from modulo where indice = ?1)) x where p.id_menu = x.id_menu group by p.id_menu, p.id_rol) xx", nativeQuery = true)    
    List<VPermisoRol> findByIndiceModulo(String indiceModulo);
    
}
