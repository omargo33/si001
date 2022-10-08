package com.qapaq.gs00100.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad JPA creada desde la consulta: "SELECT id_permiso, id_menu, id_rol, crear,
 * actualizar, borrar, ver_auditoria, usuario, usuario_fecha, usuario_programa
 * FROM GS_001_00.permiso"
 * 
 * Con codigo autonumerico en la columna id_permiso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
@Entity
@Table(name = "permiso", schema = "GS_001_00")
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long idPermiso;
    
    @Column(name = "id_menu")
    private Long idMenu;
    
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "crear", length = 8)
    private String crear;
    @Column(name = "actualizar", length = 8)
    private String actualizar;
    @Column(name = "borrar", length = 8)
    private String borrar;
    @Column(name = "ver_auditoria", length = 8)
    private String verAuditoria;
    @Column(name = "usuario", length = 128)
    private String usuario;
    @Column(name = "usuario_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFecha;
    @Column(name = "usuario_programa", length = 256)
    private String usuarioPrograma;
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", insertable = false, updatable = false)
    @OneToMany
    private List<Menu> menuList;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    @OneToMany
    private List<Rol> rolList;

    /**
     * Metodo para crear la clase Permiso.
     * 
     */
    public Permiso() {
        super();
    }

    /*
     * Metodos de acceso a los atributos de la clase
     */

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getCrear() {
        return crear;
    }

    public void setCrear(String crear) {
        this.crear = crear;
    }

    public String getActualizar() {
        return actualizar;
    }

    public void setActualizar(String actualizar) {
        this.actualizar = actualizar;
    }

    public String getBorrar() {
        return borrar;
    }

    public void setBorrar(String borrar) {
        this.borrar = borrar;
    }

    public String getVerAuditoria() {
        return verAuditoria;
    }

    public void setVerAuditoria(String verAuditoria) {
        this.verAuditoria = verAuditoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getUsuarioFecha() {
        return usuarioFecha;
    }

    public void setUsuarioFecha(Date usuarioFecha) {
        this.usuarioFecha = usuarioFecha;
    }

    public String getUsuarioPrograma() {
        return usuarioPrograma;
    }

    public void setUsuarioPrograma(String usuarioPrograma) {
        this.usuarioPrograma = usuarioPrograma;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    /**
     * Override del metodo toString para la clase Permiso.
     */
    @Override
    public String toString() {
        return "Permiso [idPermiso=" + idPermiso + ", idMenu=" + idMenu + ", idRol=" + idRol + ", crear=" + crear
                + ", actualizar=" + actualizar + ", borrar=" + borrar + ", verAuditoria=" + verAuditoria + ", usuario="
                + usuario + ", usuarioFecha=" + usuarioFecha + ", usuarioPrograma=" + usuarioPrograma + ", menuList="
                + menuList + ", rolList=" + rolList + "]";
    }
}
