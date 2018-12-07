/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.JornadasFacadeLocal;
import com.appschool.model.Jornadas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class JornadasController implements Serializable {

    private Jornadas jornada;
    @EJB
    private JornadasFacadeLocal jornadasEJB;
    private List<Jornadas> lstJornadas;

    @PostConstruct
    public void init() {
        jornada = new Jornadas();
        lstJornadas = jornadasEJB.findAll();
    }

    public void operar() {

        try {
            if (jornada.getIdJornada() == null) {
                jornadasEJB.create(jornada);
            } else {
                jornadasEJB.edit(jornada);
            }
        } catch (Exception e) {
        }
    }

    public void eliminar() {
        try {
            jornadasEJB.remove(jornada);
        } catch (Exception e) {
        }
    }

    public Jornadas getJornada() {
        return jornada;
    }

    public void setJornada(Jornadas jornada) {
        this.jornada = jornada;
    }

    public List<Jornadas> getLstJornadas() {
        return lstJornadas;
    }

    public void setLstJornadas(List<Jornadas> lstJornadas) {
        this.lstJornadas = lstJornadas;
    }

}
