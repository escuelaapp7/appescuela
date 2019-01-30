/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.PorcentajeEvaluacionFacadeLocal;
import com.appschool.model.PorcentajeEvaluacion;
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
public class PorcentajeEvaluacionController implements Serializable{
    
    private PorcentajeEvaluacion porcentajeEvaluacion;
    @EJB
    private PorcentajeEvaluacionFacadeLocal porcentajeEvaluacionEJB;
    private List<PorcentajeEvaluacion> lstPorcentajeEvaluaciones;
    
    @PostConstruct
    public void init(){
    porcentajeEvaluacion = new PorcentajeEvaluacion();
    lstPorcentajeEvaluaciones= porcentajeEvaluacionEJB.findAll();
    }

    public PorcentajeEvaluacion getPorcentajeEvaluacion() {
        return porcentajeEvaluacion;
    }

    public void setPorcentajeEvaluacion(PorcentajeEvaluacion porcentajeEvaluacion) {
        this.porcentajeEvaluacion = porcentajeEvaluacion;
    }

    public List<PorcentajeEvaluacion> getLstPorcentajeEvaluaciones() {
        return lstPorcentajeEvaluaciones;
    }

    public void setLstPorcentajeEvaluaciones(List<PorcentajeEvaluacion> lstPorcentajeEvaluaciones) {
        this.lstPorcentajeEvaluaciones = lstPorcentajeEvaluaciones;
    }
    
    
   
}
