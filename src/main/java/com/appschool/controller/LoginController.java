/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.MenurolFacadeLocal;
import com.appschool.ejb.MenusFacadeLocal;
import com.appschool.ejb.UsuariosFacadeLocal;
import com.appschool.model.Menurol;
import com.appschool.model.Menus;
import com.appschool.model.Personas;
import com.appschool.model.Roles;
import com.appschool.model.Rolesusuario;
import com.appschool.model.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author said
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private Rolesusuario rolUsuario;
    @Autowired
    private Usuarios usuario;
    private Roles rol;
    private MenuModel model;
    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    @EJB
    private MenurolFacadeLocal menuRolEJB;
    @EJB
    private MenusFacadeLocal menuEJB;
    private List<Usuarios> lstUsuarios;
    private List<Menurol> lstMenuRol;
    private Personas persona;

    @PostConstruct
    public void init() {
        rol = new Roles();
        persona = new Personas();
        usuario = new Usuarios();
        model = new DefaultMenuModel();
        lstUsuarios = usuariosEJB.findAll();
        lstMenuRol = menuRolEJB.findAll();
        insertarMenusPrimeravez();
    }

    public void registarUsuario() {
        try {
            usuario.setIdPersona(persona);
            usuariosEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.getMessage().concat(" Comuniquese con el administrador de la aplicación.")));
        }
    }

    public void modificarUsuario() {
        try {

            usuariosEJB.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se midifico exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.getMessage().concat(" Comuniquese con el administrador de la aplicación.")));
        }
    }

    public void eliminarUsuario() {
        try {
            usuariosEJB.remove(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Usuario Eliminado con éxito"));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!" + e.getMessage()));
        }
    }

    public void registarRolUsuario() {
        try {
            rolUsuario.setIdRol(rol);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.getMessage().concat(" Comuniquese con el administrador de la aplicación.")));
        }
    }

    public String iniciarSesion() {
        String redireccion = null;

        try {
            Usuarios us;
            us = usuariosEJB.iniciarSesion(usuario);

            if (us != null) {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "/plantilla.xhtml?faces-redirect=true";
                usuario = us;

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Bienvenido" + usuario.getIdPersona().getNombres()));
                usuario.setRolesusuarioList(usuariosEJB.rolesPorUsuario(us));
                for (Rolesusuario rolesusuario : usuario.getRolesusuarioList()) {
                    rolesusuario.getIdRol().setMenurolList(usuariosEJB.menuPorRoles(rolesusuario.getIdRol()));
                }
                verificarPermisos(usuario);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Las credenciales no coinciden."));
            }
        } catch (Exception e) {

        }

        return redireccion;
    }

    public void cerrarSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public void verificarSesion() {
        try {

            FacesContext contexto = FacesContext.getCurrentInstance();
            Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml?faces-redirect=true");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Usted no ha iniciado sesión"));

            }

        } catch (Exception e) {
        }
    }

    public void verificarPermisos(Usuarios usuario) {
        usuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        for (Rolesusuario rolesusuario : usuario.getRolesusuarioList()) {

            for (Menurol menurol : rolesusuario.getIdRol().getMenurolList()) {
                if (menurol.getIdMenu().getTipo().equals("S")) {
                    DefaultSubMenu firstSubmenu = new DefaultSubMenu(menurol.getIdMenu().getNombre());
                    for (Menurol items : menurol.getIdRol().getMenurolList()) {
                        if (items.getIdMenu().getCodigoSubmenu() == menurol.getIdMenu()) {
                            DefaultMenuItem item = new DefaultMenuItem(items.getIdMenu().getNombre());
                            item.setUrl(items.getIdMenu().getUrl());
                            item.setIcon(items.getIdMenu().getIcono());
                            firstSubmenu.addElement(item);
                        }
                    }
                    model.addElement(firstSubmenu);

                }
            }
        }

//        for (Rolesusuario roles : usuario.getRolesusuarioList()) {
//
//            for (Menurol menurol : roles.getIdRol().getMenurolList()) {
//
//                if (menurol.getIdMenu().getTipo().equals("S")) {
//                    DefaultSubMenu firstSubmenu = new DefaultSubMenu(menurol.getIdMenu().getNombre());
//
//                    for (Menurol item1 : roles.getIdRol().getMenurolList()) {
//                        Menus submenu = item1.getIdMenu();
//                        if (submenu != null) {
//                            if (submenu.getIdMenu().equals(menurol.getIdMenu().getIdMenu())) {
//                                DefaultMenuItem item = new DefaultMenuItem(menurol.getIdMenu().getNombre());
//                                item.setUrl(menurol.getIdMenu().getUrl());
//                                item.setIcon(menurol.getIdMenu().getIcono());
//                                firstSubmenu.addElement(item);
//                            }
//                        }
//                    }
//                    model.addElement(firstSubmenu);
//                } else {
//                    if ((menurol.getIdMenu().getCodigoSubmenu().getCodigoSubmenu()) == null) {
//                        DefaultMenuItem item = new DefaultMenuItem(menurol.getIdMenu().getNombre());
//                        item.setUrl(menurol.getIdMenu().getUrl());
//                        item.setIcon(menurol.getIdMenu().getIcono());
//                        model.addElement(item);
//
//                    }
//                }
//            }
//        }
    }

    public void insertarMenusPrimeravez() {
        List<Menus> lista = null;
        lista= menuEJB.findAll();
        if (lista.size()<=0) {
            usuariosEJB.crearMenusPrimeraVez(); 
        } 
    }

    public Rolesusuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Rolesusuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public List<Usuarios> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Usuarios> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

}
