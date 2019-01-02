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
import java.io.Serializable;
import java.util.ArrayList;
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
public class CalificacionesController implements Serializable {

    private Impartir impartir;
    private Evaluaciones evaluacion;
    private Periodos periodo;
    private Matriculas matricula;
    private Calificaciones calificacion;

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

    @PostConstruct
    public void init() {
        impartir = new Impartir();
        evaluacion = new Evaluaciones();
        periodo = new Periodos();
        matricula = new Matriculas();
        calificacion = new Calificaciones();
        lstImpartir = impartirEJB.findAll();
        lstEvaluaciones = evaluacionesEJB.findAll();
        lstPeriodos = periodosEJB.findAll();
        lstCalificaciones = calificaionesEJB.findAll();
        lstMatriculas = new ArrayList<>();

    }

    public void alumnosPorMateria() {
        lstMatriculas = calificaionesEJB.alumnosPorAsignacion(impartir);
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

}
