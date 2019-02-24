/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.RolesFacadeLocal;
import com.appschool.ejb.RolesusuarioFacadeLocal;
import com.appschool.model.Roles;
import com.appschool.model.Rolesusuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RolesController implements Serializable {

    private Roles rol;
    private Rolesusuario rolUsuario;
    private Rolesusuario rolUsuarioEliminar;

    @EJB
    private RolesFacadeLocal rolesEJB;
    @EJB
    private RolesusuarioFacadeLocal rolesUsuarioEJB;
    private List<Roles> lstRoles;

    @PostConstruct
    public void init() {
        rolUsuarioEliminar = new Rolesusuario();
        rol = new Roles();
        rolUsuario = new Rolesusuario();
        lstRoles = rolesEJB.findAll();
    }

    public void operar() {

        try {
            if (rol.getIdRol() == null) {
                rolesEJB.create(rol);
            } else {
                rolesEJB.edit(rol);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            rolesEJB.remove(rol);
        } catch (Exception e) {
        }
    }

    public void agregarRolUsuario() {
        rolesUsuarioEJB.create(rolUsuario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Agrego rol exitosamente"));
        rolUsuario.getIdUsuario().setRolesusuarioList(rolesUsuarioEJB.rolesPorUsuario(rolUsuario.getIdUsuario()));
    }

    public void eliminarRolUsuario() {
        rolesUsuarioEJB.remove(rolUsuarioEliminar);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino rol exitosamente"));
        rolUsuario.getIdUsuario().setRolesusuarioList(rolesUsuarioEJB.rolesPorUsuario(rolUsuario.getIdUsuario()));
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public List<Roles> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<Roles> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public Rolesusuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Rolesusuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Rolesusuario getRolUsuarioEliminar() {
        return rolUsuarioEliminar;
    }

    public void setRolUsuarioEliminar(Rolesusuario rolUsuarioEliminar) {
        this.rolUsuarioEliminar = rolUsuarioEliminar;
    }
    

}
