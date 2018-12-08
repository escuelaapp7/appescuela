/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.PeriodosFacadeLocal;
import com.appschool.model.Periodos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class PeriodosController implements Serializable {

    private Periodos periodo;
    @EJB
    private PeriodosFacadeLocal periodosEJB;
    private List<Periodos> lstPeriodos;

    @PostConstruct
    public void init() {
        periodo = new Periodos();
        lstPeriodos = periodosEJB.findAll();
    }

    public void operar() {

        try {
            if (periodo.getIdPeriodo() == null) {
                periodosEJB.create(periodo);
            } else {
                periodosEJB.edit(periodo);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            periodosEJB.remove(periodo);
        } catch (Exception e) {
        }
    }

    public Periodos getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodos periodo) {
        this.periodo = periodo;
    }

    public List<Periodos> getLstPeriodos() {
        return lstPeriodos;
    }

    public void setLstPeriodos(List<Periodos> lstPeriodos) {
        this.lstPeriodos = lstPeriodos;
    }

}
