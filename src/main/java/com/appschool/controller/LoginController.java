/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.RolesusuarioFacadeLocal;
import com.appschool.ejb.UsuariosFacadeLocal;
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

/**
 *
 * @author said
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private Rolesusuario rolUsuario;
    private Usuarios usuario;
    private Roles rol;
    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    @EJB
    private RolesusuarioFacadeLocal rolesUsuarioEJB;
    private List<Usuarios> lstUsuarios;
    private Personas persona;

    @PostConstruct
    public void init() {
        rol = new Roles();
        persona = new Personas();
        usuario = new Usuarios();
        lstUsuarios = usuariosEJB.findAll();
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
            rolesUsuarioEJB.create(rolUsuario);
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

}
