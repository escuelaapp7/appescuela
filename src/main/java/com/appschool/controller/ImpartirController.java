/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.AsignaturasFacadeLocal;
import com.appschool.ejb.GradosFacadeLocal;
import com.appschool.ejb.ImpartirFacadeLocal;
import com.appschool.ejb.JornadasFacadeLocal;
import com.appschool.ejb.ProfesoresFacadeLocal;
import com.appschool.ejb.SeccionesFacadeLocal;
import com.appschool.model.Asignaturas;
import com.appschool.model.Grados;
import com.appschool.model.Impartir;
import com.appschool.model.Jornadas;
import com.appschool.model.Profesores;
import com.appschool.model.Secciones;
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
public class ImpartirController implements Serializable {

    private Profesores profesor;
    private Asignaturas asignatura;
    private Grados grado;
    private Secciones seccion;
    private Jornadas jornada;
    private Impartir impartir;

    @EJB
    private ProfesoresFacadeLocal profesoresEJB;
    @EJB
    private AsignaturasFacadeLocal asignaturasEJB;
    @EJB
    private GradosFacadeLocal gradosEJB;
    @EJB
    private SeccionesFacadeLocal seccionesEJB;
    @EJB
    private JornadasFacadeLocal jornadasEJB;
    @EJB
    private ImpartirFacadeLocal impartirEJB;

    private List<Profesores> lstProfesores;
    private List<Asignaturas> lstAsignaturas;
    private List<Grados> lstGrados;
    private List<Secciones> lstSecciones;
    private List<Jornadas> lstJornadas;
    private List<Impartir> lstImpartir;

    @PostConstruct
    public void init() {
        profesor = new Profesores();
        asignatura = new Asignaturas();
        grado = new Grados();
        seccion = new Secciones();
        jornada = new Jornadas();
        impartir = new Impartir();
        lstProfesores = profesoresEJB.findAll();
        lstAsignaturas = asignaturasEJB.findAll();
        lstGrados = gradosEJB.findAll();
        lstSecciones = seccionesEJB.findAll();
        lstJornadas = jornadasEJB.findAll();
        lstImpartir = impartirEJB.findAll();
    }

    public void operarImpartir() {
        try {
            if (impartir.getIdImpartir() == null) {
                impartir.setIdProfesor(profesor);
                impartir.setIdAsignatura(asignatura);
                impartir.setIdGrado(grado);
                impartir.setIdSeccion(seccion);
                impartir.setIdJornada(jornada);
                impartirEJB.create(impartir);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro exitosamente"));
            } else {
                impartirEJB.edit(impartir);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Modifico exitosamente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarImpartir() {
        try {
            impartirEJB.remove(impartir);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se elimino exitosamente"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
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

    public Impartir getImpartir() {
        return impartir;
    }

    public void setImpartir(Impartir impartir) {
        this.impartir = impartir;
    }

    public List<Asignaturas> getLstAsignaturas() {
        return lstAsignaturas;
    }

    public void setLstAsignaturas(List<Asignaturas> lstAsignaturas) {
        this.lstAsignaturas = lstAsignaturas;
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

    public List<Impartir> getLstImpartir() {
        return lstImpartir;
    }

    public void setLstImpartir(List<Impartir> lstImpartir) {
        this.lstImpartir = lstImpartir;
    }

    public List<Profesores> getLstProfesores() {
        return lstProfesores;
    }

    public void setLstProfesores(List<Profesores> lstProfesores) {
        this.lstProfesores = lstProfesores;
    }
    
    
}
