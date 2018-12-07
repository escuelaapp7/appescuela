/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.AsignaturasFacadeLocal;
import com.appschool.model.Asignaturas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class AsignaturasController implements Serializable {

    private Asignaturas asignatura;
    @EJB
    private AsignaturasFacadeLocal asignaturasEJB;
    private List<Asignaturas> lstAsignaturas;

    @PostConstruct
    public void init() {
        asignatura = new Asignaturas();
        lstAsignaturas = asignaturasEJB.findAll();
    }

    public void operar() {

        try {
            if (asignatura.getIdAsignatura() == null) {
                asignaturasEJB.create(asignatura);
            } else {
                asignaturasEJB.edit(asignatura);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            asignaturasEJB.remove(asignatura);
        } catch (Exception e) {
        }
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    public List<Asignaturas> getLstAsignaturas() {
        return lstAsignaturas;
    }

    public void setLstAsignaturas(List<Asignaturas> lstAsignaturas) {
        this.lstAsignaturas = lstAsignaturas;
    }
    
    
}
