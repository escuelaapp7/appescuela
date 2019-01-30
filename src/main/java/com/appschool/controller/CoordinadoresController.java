/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.CoordinadoresFacadeLocal;
import com.appschool.ejb.GradosFacadeLocal;
import com.appschool.ejb.JornadasFacadeLocal;
import com.appschool.ejb.ProfesoresFacadeLocal;
import com.appschool.ejb.SeccionesFacadeLocal;
import com.appschool.model.Coordinadorgrado;
import com.appschool.model.Grados;
import com.appschool.model.Jornadas;
import com.appschool.model.Profesores;
import com.appschool.model.Secciones;
import java.io.Serializable;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.ManagedBean;
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
@ManagedBean
public class CoordinadoresController implements Serializable {

    private Grados grado;
    private Secciones seccion;
    private Jornadas jornada;
    private Profesores profesor;
    private Coordinadorgrado coordinador;

    @EJB
    private GradosFacadeLocal gradosEJB;
    @EJB
    private SeccionesFacadeLocal seccionesEJB;
    @EJB
    private JornadasFacadeLocal jornadasEJB;
    @EJB
    private ProfesoresFacadeLocal profesoresEJB;
    @EJB
    private CoordinadoresFacadeLocal coordinadoresEJB;

    private List<Grados> lstGrados;
    private List<Secciones> lstSecciones;
    private List<Jornadas> lstJornadas;
    private List<Profesores> lstProfesores;
    private List<Coordinadorgrado> lstCoordinadores;
    private List<Integer> lstAnios;

    @PostConstruct
    public void init() {
        grado = new Grados();
        seccion = new Secciones();
        jornada = new Jornadas();
        profesor = new Profesores();
        coordinador = new Coordinadorgrado();
        lstGrados = gradosEJB.findAll();
        lstSecciones = seccionesEJB.findAll();
        lstJornadas = jornadasEJB.findAll();
        lstProfesores = profesoresEJB.findAll();
        lstCoordinadores = coordinadoresEJB.findAll();
        lstAnios = new ArrayList<>();
        llenarAnios();

    }

    public void operarCoordinador() {
        try {
            if (coordinador.getIdCoordinadorGrado() == null) {
                coordinador.setIdGrado(grado);
                coordinador.setIdSeccion(seccion);
                coordinador.setIdJornada(jornada);
                coordinador.setIdProfesor(profesor);
                coordinadoresEJB.create(coordinador);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
            } else {
                coordinadoresEJB.edit(coordinador);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modifico exitosamente"));
            }
        } catch (Exception e) {
        }
    }

    public List<Integer> llenarAnios() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for (int i = 0; i < 2; i++) {
            lstAnios.add(year + i);
        }
        return lstAnios;

    }

    public void eliminarCoordinador() {
        try {
            coordinadoresEJB.remove(coordinador);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino exitosamente"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Grados getGrado() {
        return grado;
    }

    public void setGrado(Grados grado) {
        this.grado = grado;
    }

    public Secciones getSeccion() {
        return seccion;
    }

    public void setSeccion(Secciones seccion) {
        this.seccion = seccion;
    }

    public Jornadas getJornada() {
        return jornada;
    }

    public void setJornada(Jornadas jornada) {
        this.jornada = jornada;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public Coordinadorgrado getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Coordinadorgrado coordinador) {
        this.coordinador = coordinador;
    }

    public List<Grados> getLstGrados() {
        return lstGrados;
    }

    public void setLstGrados(List<Grados> lstGrados) {
        this.lstGrados = lstGrados;
    }

    public List<Secciones> getLstSecciones() {
        return lstSecciones;
    }

    public void setLstSecciones(List<Secciones> lstSecciones) {
        this.lstSecciones = lstSecciones;
    }

    public List<Jornadas> getLstJornadas() {
        return lstJornadas;
    }

    public void setLstJornadas(List<Jornadas> lstJornadas) {
        this.lstJornadas = lstJornadas;
    }

    public List<Profesores> getLstProfesores() {
        return lstProfesores;
    }

    public void setLstProfesores(List<Profesores> lstProfesores) {
        this.lstProfesores = lstProfesores;
    }

    public List<Coordinadorgrado> getLstCoordinadores() {
        return lstCoordinadores;
    }

    public void setLstCoordinadores(List<Coordinadorgrado> lstCoordinadores) {
        this.lstCoordinadores = lstCoordinadores;
    }

    public List<Integer> getLstAnios() {
        return lstAnios;
    }

    public void setLstAnios(List<Integer> lstAnios) {
        this.lstAnios = lstAnios;
    }

    
}
