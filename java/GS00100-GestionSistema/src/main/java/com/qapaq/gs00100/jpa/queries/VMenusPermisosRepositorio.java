package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.gs00100.jpa.model.VMenusPermisos;


/**
 * Interface para la clase VMenusPermisos.
 * 
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-23
 * 
 */
public interface VMenusPermisosRepositorio extends JpaRepository<VMenusPermisos, Long> {

    /**
     * Metodo para buscar todos los objetos por nick, con una consulta nativa.
     */
    @Query(value = "select xx.* from ( select vm.id_menu AS id_menus_permisos, vm.nick, vm.indice_modulo, vm.nombre_modulo, vm.contexto, vm.id_menu, vm.tipo, vm.indice_menu, vm.nombre_menu, vm.task_flow, vm.task_flow_informacion, vm.orden, GS_001_00.permiso_menu_crear(vm.id_menu, vm.nick) crear, GS_001_00.permiso_menu_actualizar(vm.id_menu, vm.nick) actualizar, GS_001_00.permiso_menu_borrar(vm.id_menu, vm.nick) borrar, GS_001_00.permiso_menu_ver_auditoria(vm.id_menu, vm.nick) ver_auditoria from GS_001_00.v_menu vm order by vm.orden ) xx where xx.nick = ?1", nativeQuery = true)
    public List<VMenusPermisos> findAllByNick(String nick);
}