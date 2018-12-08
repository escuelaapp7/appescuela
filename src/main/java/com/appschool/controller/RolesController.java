/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.RolesFacadeLocal;
import com.appschool.model.Roles;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class RolesController implements Serializable {

    private Roles rol;
    @EJB
    private RolesFacadeLocal rolesEJB;
    private List<Roles> lstRoles;

    @PostConstruct
    public void init() {
        rol = new Roles();
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

}
