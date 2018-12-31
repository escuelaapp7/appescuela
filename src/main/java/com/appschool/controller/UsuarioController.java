/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.RolesFacadeLocal;
import com.appschool.ejb.RolesusuarioFacadeLocal;
import com.appschool.ejb.UsuariosFacadeLocal;
import com.appschool.model.Personas;
import com.appschool.model.Roles;
import com.appschool.model.Rolesusuario;
import com.appschool.model.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable {

    private Personas persona;
    private Usuarios usuario;
    private Roles rol;
    private Rolesusuario rolUsuario;

    @EJB
    private UsuariosFacadeLocal usuariosEJB;
    @EJB
    private RolesusuarioFacadeLocal rolesUsuarioEJB;
    @EJB
    private RolesFacadeLocal rolesEJB;

    private List<Usuarios> lstUsuarios;
    private List<Roles> lstRoles;
    private List<Roles> lstUsuarioRoles;
    
    
    
    
    @PostConstruct
    public void init() {
        rol = new Roles();
        persona = new Personas();
        usuario = new Usuarios();
        lstUsuarios = usuariosEJB.findAll();
        lstRoles = rolesEJB.findAll();
        lstUsuarioRoles= new ArrayList<>();

    }

    public void operarUsuario() {
        try {
            if (usuario.getIdUsuario() != null) {
                usuariosEJB.edit(usuario);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se midifico exitosamente"));
            } else {
                usuario.setPassword(usuario.getUsuario());
                usuariosEJB.create(usuario);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente, el password es el mismo nombre de usuario"));

            }

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

    public void operarAsignacionRolUsuario() {

        if (rolUsuario != null) {
            rolesUsuarioEJB.edit(rolUsuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico el rol exitosamente"));
        } else {
            usuario.setIdPersona(persona);
            rolesUsuarioEJB.create(rolUsuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
        }
    }

    public void restablecerContrasena() {
        usuario.setPassword(usuario.getUsuario());
        usuariosEJB.edit(usuario);
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

    public List<Roles> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<Roles> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public List<Roles> getLstUsuarioRoles() {
        return lstUsuarioRoles;
    }

    public void setLstUsuarioRoles(List<Roles> lstUsuarioRoles) {
        this.lstUsuarioRoles = lstUsuarioRoles;
    }
    
    

}
