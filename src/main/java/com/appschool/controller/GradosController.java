/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.GradosFacadeLocal;
import com.appschool.model.Grados;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class GradosController implements Serializable {
    
    private Grados grado;
    @EJB
    private GradosFacadeLocal gradosEJB;
    private List<Grados> lstGrados;
    
    @PostConstruct
    public void init() {
        grado = new Grados();
        lstGrados = gradosEJB.findAll();
    }
    
    public void operar() {
        
        try {
            if (grado.getIdGrado() == null) {
                gradosEJB.create(grado);
            } else {
                gradosEJB.edit(grado);
            }
        } catch (Exception e) {
        }
    }
    
    public void eliminar() {
        try {
            gradosEJB.remove(grado);
        } catch (Exception e) {
        }
    }
    
    public Grados getGrado() {
        return grado;
    }
    
    public void setGrado(Grados grado) {
        this.grado = grado;
    }
    
    public List<Grados> getLstGrados() {
        return lstGrados;
    }
    
    public void setLstGrados(List<Grados> lstGrados) {
        this.lstGrados = lstGrados;
    }
    
}

