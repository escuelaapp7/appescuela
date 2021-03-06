/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.EvaluacionesFacadeLocal;
import com.appschool.model.Evaluaciones;
import com.appschool.model.Grados;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;

@Named
@ViewScoped
public class EvaluacionesController implements Serializable {

    private Evaluaciones evaluacion;
    @EJB
    private EvaluacionesFacadeLocal evaluacionesEJB;
    private List<Evaluaciones> lstEvaluaciones;

    @PostConstruct
    public void init() {
        evaluacion = new Evaluaciones();
        lstEvaluaciones = evaluacionesEJB.findAll();
    }

    public void operar() {

        try {
            if (evaluacion.getIdEvaluacion() == null) {
                evaluacionesEJB.create(evaluacion);
            } else {
                evaluacionesEJB.edit(evaluacion);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            evaluacionesEJB.remove(evaluacion);
        } catch (Exception e) {
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Evaluaciones getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluaciones evaluacion) {
        this.evaluacion = evaluacion;
    }

    public List<Evaluaciones> getLstEvaluaciones() {
        return lstEvaluaciones;
    }

    public void setLstEvaluaciones(List<Evaluaciones> lstEvaluaciones) {
        this.lstEvaluaciones = lstEvaluaciones;
    }

}
