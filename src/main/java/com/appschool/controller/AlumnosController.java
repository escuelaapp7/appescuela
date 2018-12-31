/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.AlumnosFacadeLocal;
import com.appschool.ejb.ParentezcosFacadeLocal;
import com.appschool.model.Alumnos;
import com.appschool.model.Encargados;
import com.appschool.model.Parentezcos;
import com.appschool.model.Personas;
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
public class AlumnosController implements Serializable {

    private Personas persona;
    private Alumnos alumno;
    @EJB
    private AlumnosFacadeLocal ejbAlumnos;
    @EJB
    private ParentezcosFacadeLocal parentezcoEJB;
    private List<Alumnos> lstAlumnos;
    private List<Parentezcos> lstParentezcos;

    @PostConstruct
    public void init() {
        persona = new Personas();
        alumno = new Alumnos();
        lstAlumnos = ejbAlumnos.findAll();
        lstParentezcos = parentezcoEJB.findAll();
    }

    public void operar() {
        try {
            if (alumno.getIdAlumno() == null) {
                alumno.setIdPersona(persona);
                ejbAlumnos.create(alumno);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
            } else {
                ejbAlumnos.edit(alumno);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico exitosamente"));
            }
        } catch (Exception e) {
        }
    }

    public void eliminarAlumno() {
        try {
            ejbAlumnos.remove(alumno);
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

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public List<Alumnos> getLstAlumnos() {
        return lstAlumnos;
    }

    public void setLstAlumnos(List<Alumnos> lstAlumnos) {
        this.lstAlumnos = lstAlumnos;
    }



    public void setLstParentezcos(List<Parentezcos> lstParentezcos) {
        this.lstParentezcos = lstParentezcos;
    }

}
