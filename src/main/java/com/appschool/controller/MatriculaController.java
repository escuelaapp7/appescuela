/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.AlumnosFacadeLocal;
import com.appschool.ejb.CoordinadoresFacadeLocal;
import com.appschool.ejb.EncargadosFacadeLocal;
import com.appschool.ejb.MatriculasFacadeLocal;
import com.appschool.ejb.ParentezcosFacadeLocal;
import com.appschool.model.Alumnos;
import com.appschool.model.Coordinadorgrado;
import com.appschool.model.Encargados;
import com.appschool.model.Matriculas;
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
public class MatriculaController implements Serializable {
    private Personas persona;
    private Encargados encargado;
    private Parentezcos parentezco;
    private Alumnos alumno;
    private Coordinadorgrado coordinador;
    private Matriculas matricula;

    @EJB
    private EncargadosFacadeLocal encargadosEJB;
    @EJB
    private ParentezcosFacadeLocal parentezcosEJB;
    @EJB
    private AlumnosFacadeLocal alumnosEJB;
    @EJB
    private CoordinadoresFacadeLocal coordinadoresEJB;
    @EJB
    private MatriculasFacadeLocal matriculaEJB;

    List<Encargados> lstEncargados;
    List<Parentezcos> lstParentezcos;
    List<Alumnos> lstAlumnos;
    List<Coordinadorgrado> lstCoordinadores;
    List<Matriculas> lstMatriculas;

    @PostConstruct
    public void init() {
        persona = new Personas();
        encargado = new Encargados();
        parentezco = new Parentezcos();
        alumno = new Alumnos();
        coordinador = new Coordinadorgrado();
        matricula= new Matriculas();
        lstEncargados = encargadosEJB.findAll();
        lstParentezcos = parentezcosEJB.findAll();
        lstAlumnos = alumnosEJB.findAll();
        lstCoordinadores = coordinadoresEJB.findAll();
        lstMatriculas = matriculaEJB.findAll();
    }

    public void matricularOperar() {
        try {
            if (matricula.getIdMatricula() == null) {
                encargado.setIdPersona(persona);
                matricula.setIdEncargado(encargado);
                matricula.setIdParentezco(parentezco);
                matricula.setIdAlumno(alumno);
                matricula.setIdCoordinadorGrado(coordinador);
                matriculaEJB.create(matricula);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
            } else {
                matriculaEJB.edit(matricula);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico exitosamente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarMatricula() {
        try {
            matriculaEJB.remove(matricula);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino exitosamente"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Encargados getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargados encargado) {
        this.encargado = encargado;
    }

    public Parentezcos getParentezco() {
        return parentezco;
    }

    public void setParentezco(Parentezcos parentezco) {
        this.parentezco = parentezco;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Coordinadorgrado getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Coordinadorgrado coordinador) {
        this.coordinador = coordinador;
    }

    public Matriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(Matriculas matricula) {
        this.matricula = matricula;
    }

    public List<Encargados> getLstEncargados() {
        return lstEncargados;
    }

    public void setLstEncargados(List<Encargados> lstEncargados) {
        this.lstEncargados = lstEncargados;
    }

    public List<Parentezcos> getLstParentezcos() {
        return lstParentezcos;
    }

    public void setLstParentezcos(List<Parentezcos> lstParentezcos) {
        this.lstParentezcos = lstParentezcos;
    }

    public List<Alumnos> getLstAlumnos() {
        return lstAlumnos;
    }

    public void setLstAlumnos(List<Alumnos> lstAlumnos) {
        this.lstAlumnos = lstAlumnos;
    }

    public List<Coordinadorgrado> getLstCoordinadores() {
        return lstCoordinadores;
    }

    public void setLstCoordinadores(List<Coordinadorgrado> lstCoordinadores) {
        this.lstCoordinadores = lstCoordinadores;
    }

    public List<Matriculas> getLstMatriculas() {
        return lstMatriculas;
    }

    public void setLstMatriculas(List<Matriculas> lstMatriculas) {
        this.lstMatriculas = lstMatriculas;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
    
    
    

}
