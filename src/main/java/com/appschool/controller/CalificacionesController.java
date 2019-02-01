/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.controller;

import com.appschool.ejb.CalificacionesFacadeLocal;
import com.appschool.ejb.EvaluacionesFacadeLocal;
import com.appschool.ejb.ImpartirFacadeLocal;
import com.appschool.ejb.MatriculasFacadeLocal;
import com.appschool.ejb.PeriodosFacadeLocal;
import com.appschool.model.Calificaciones;
import com.appschool.model.Evaluaciones;
import com.appschool.model.Impartir;
import com.appschool.model.Matriculas;
import com.appschool.model.Periodos;
import com.appschool.model.Personas;
import com.appschool.model.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.contextmenu.ContextMenu;

/**
 *
 * @author said
 */
@Named
@ViewScoped
public class CalificacionesController implements Serializable {

    private Personas persona;
    private Impartir impartir;
    private Evaluaciones evaluacion;
    private Periodos periodo;
    private Matriculas matricula;
    private Calificaciones calificacion;
    private Usuarios usuario;

    @EJB
    private ImpartirFacadeLocal impartirEJB;
    @EJB
    private EvaluacionesFacadeLocal evaluacionesEJB;
    @EJB
    private PeriodosFacadeLocal periodosEJB;
    @EJB
    private MatriculasFacadeLocal matriculasEJB;
    @EJB
    private CalificacionesFacadeLocal calificaionesEJB;

    private List<Impartir> lstImpartir;
    private List<Evaluaciones> lstEvaluaciones;
    private List<Periodos> lstPeriodos;
    private List<Matriculas> lstMatriculas;
    private List<Calificaciones> lstCalificaciones;
    private List<Impartir> lstImpartirPorUsuario;

    @PostConstruct
    public void init() {
        persona = new Personas();
        impartir = new Impartir();
        evaluacion = new Evaluaciones();
        periodo = new Periodos();
        matricula = new Matriculas();
        calificacion = new Calificaciones();
        usuario = new Usuarios();
        lstImpartir = impartirEJB.findAll();
        lstEvaluaciones = evaluacionesEJB.findAll();
        lstPeriodos = periodosEJB.findAll();
        lstCalificaciones = calificaionesEJB.findAll();
        lstMatriculas = new ArrayList<>();
        lstImpartirPorUsuario = new ArrayList<>();
        impartirPorProfesor();
    }

    public void impartirPorProfesor() {
        usuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        lstImpartirPorUsuario = calificaionesEJB.impartirPorUsuario(usuario);
    }

    public void alumnosPorMateria() {
        lstMatriculas = calificaionesEJB.alumnosPorAsignacion(impartir);
        for (Matriculas matricula : lstMatriculas) {
            matricula.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matricula, impartir));
            if (matricula.getCalificacionesList().size() <= 0) {
                matricula.setCalificacionesList(new ArrayList<Calificaciones>());
            }
        }
    }

    public Impartir getImpartir() {
        return impartir;
    }

    public void setImpartir(Impartir impartir) {
        this.impartir = impartir;
    }

    public Evaluaciones getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluaciones evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Periodos getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodos periodo) {
        this.periodo = periodo;
    }

    public Matriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(Matriculas matricula) {
        this.matricula = matricula;
    }

    public Calificaciones getCalificacion() {
        return calificacion;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public void setCalificacion(Calificaciones calificacion) {
        this.calificacion = calificacion;
    }

    public List<Impartir> getLstImpartir() {
        return lstImpartir;
    }

    public void setLstImpartir(List<Impartir> lstImpartir) {
        this.lstImpartir = lstImpartir;
    }

    public List<Evaluaciones> getLstEvaluaciones() {
        return lstEvaluaciones;
    }

    public void setLstEvaluaciones(List<Evaluaciones> lstEvaluaciones) {
        this.lstEvaluaciones = lstEvaluaciones;
    }

    public List<Periodos> getLstPeriodos() {
        return lstPeriodos;
    }

    public void setLstPeriodos(List<Periodos> lstPeriodos) {
        this.lstPeriodos = lstPeriodos;
    }

    public List<Matriculas> getLstMatriculas() {
        return lstMatriculas;
    }

    public void setLstMatriculas(List<Matriculas> lstMatriculas) {
        this.lstMatriculas = lstMatriculas;
    }

    public List<Calificaciones> getLstCalificaciones() {
        return lstCalificaciones;
    }

    public void setLstCalificaciones(List<Calificaciones> lstCalificaciones) {
        this.lstCalificaciones = lstCalificaciones;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Impartir> getLstImpartirPorUsuario() {
        return lstImpartirPorUsuario;
    }

    public void setLstImpartirPorUsuario(List<Impartir> lstImpartirPorUsuario) {
        this.lstImpartirPorUsuario = lstImpartirPorUsuario;
    }

}
