/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.SeccionesFacadeLocal;
import com.appschool.model.Secciones;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class SeccionesController implements Serializable {

    private Secciones seccion;
    @EJB
    private SeccionesFacadeLocal seccionesEJB;
    private List<Secciones> lstSecciones;

    @PostConstruct
    public void init() {
        seccion = new Secciones();
        lstSecciones = seccionesEJB.findAll();
    }

    public void operar() {

        try {
            if (seccion.getIdSeccion() == null) {
                seccionesEJB.create(seccion);
            } else {
                seccionesEJB.edit(seccion);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            seccionesEJB.remove(seccion);
        } catch (Exception e) {
        }
    }

    public Secciones getSeccion() {
        return seccion;
    }

    public void setSeccion(Secciones seccion) {
        this.seccion = seccion;
    }

    public List<Secciones> getLstSecciones() {
        return lstSecciones;
    }

    public void setLstSecciones(List<Secciones> lstSeccones) {
        this.lstSecciones = lstSeccones;
    }

}
