/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.ProfesoresFacadeLocal;
import com.appschool.model.Personas;
import com.appschool.model.Profesores;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class ProfesorController implements Serializable {

    private Personas persona;
    private Profesores profesor;
    @EJB
    private ProfesoresFacadeLocal profesoresEJB;
    private List<Profesores> lstProfesores;

    @PostConstruct
    public void init() {
        persona = new Personas();
        profesor = new Profesores();
        lstProfesores = profesoresEJB.findAll();

    }

    public void operar() {
        try {
            if (profesor.getIdProfesor() == null) {
                profesor.setIdPersona(persona);
                profesoresEJB.create(profesor);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
            } else {
                profesoresEJB.edit(profesor);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico exitosamente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProfesor() {
        try {
            profesoresEJB.remove(profesor);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino exitosamente"));
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public List<Profesores> getLstProfesores() {
        return lstProfesores;
    }

    public void setLstProfesores(List<Profesores> lstProfesores) {
        this.lstProfesores = lstProfesores;
    }

}
