/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.ParentezcosFacadeLocal;
import com.appschool.model.Parentezcos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class ParentezcoController implements Serializable {

    private Parentezcos parentezco;
    @EJB
    private ParentezcosFacadeLocal parentezcoEJB;
    private List<Parentezcos> lstParentezcos;

    @PostConstruct
    public void init() {
        parentezco = new Parentezcos();
        lstParentezcos = parentezcoEJB.findAll();
    }

    public void operar() {

        try {
            if (parentezco.getIdParentezco() == null) {
                parentezcoEJB.create(parentezco);
            } else {
                parentezcoEJB.edit(parentezco);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            parentezcoEJB.remove(parentezco);
        } catch (Exception e) {
        }
    }



    public List<Parentezcos> getLstParentezcos() {
        return lstParentezcos;
    }

    public void setLstParentezcos(List<Parentezcos> lstParentezcos) {
        this.lstParentezcos = lstParentezcos;
    }

    public Parentezcos getParentezco() {
        return parentezco;
    }

    public void setParentezco(Parentezcos parentezco) {
        this.parentezco = parentezco;
    }

    
}
