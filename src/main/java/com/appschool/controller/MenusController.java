/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.MenurolFacadeLocal;
import com.appschool.ejb.MenusFacadeLocal;
import com.appschool.model.Menurol;
import com.appschool.model.Menus;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MenusController implements Serializable {

    private Menus menu;
    private Menurol menuRol;
    @EJB
    private MenusFacadeLocal menusEJB;
    @EJB
    private MenurolFacadeLocal menuRolEJB;
    private List<Menus> lstMenus;

    @PostConstruct
    public void init() {
        menu = new Menus();
        menuRol = new Menurol();
        lstMenus = menusEJB.findAll();
    }

    public void operar() {

        try {
            if (menu.getIdMenu() == null) {
                menusEJB.create(menu);
            } else {
                menusEJB.edit(menu);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            menusEJB.remove(menu);
        } catch (Exception e) {
        }
    }

    public void agregarAcceso() {
        menuRolEJB.create(menuRol);
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    public List<Menus> getLstMenus() {
        return lstMenus;
    }

    public void setLstMenus(List<Menus> lstMenus) {
        this.lstMenus = lstMenus;
    }

    public Menurol getMenuRol() {
        return menuRol;
    }

    public void setMenuRol(Menurol menuRol) {
        this.menuRol = menuRol;
    }

}
