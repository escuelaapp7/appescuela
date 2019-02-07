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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

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
        lstCalificaciones = new ArrayList<>();
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

        for (Matriculas matri : lstMatriculas) {
            matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
            if (matri.getCalificacionesList().size() <= 0) {
                matri.setCalificacionesList(new ArrayList<Calificaciones>());
            }
        }
    }

    public void registrarNota() {
        calificaionesEJB.create(calificacion);
        for (Matriculas matricula : lstMatriculas) {
            matricula.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matricula, impartir));
        }

    }

    public void onCellEdit(CellEditEvent event) {
        Double i;

        String header = event.getColumn().getHeaderText();
        Double oldValue = (Double) event.getOldValue();
        Double newValue = (Double) event.getNewValue();
        switch (header) {
            case "Nota 1 P1":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(0).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(0);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(3);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(0).getNota() + matricula.getCalificacionesList().get(1).getNota() + matricula.getCalificacionesList().get(2).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        for (Matriculas matri : lstMatriculas) {
                            matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                        }
                        calificacion = new Calificaciones();
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();

                }
                break;

            case "Nota 2 P1":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(1).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(1);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(3);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(0).getNota() + matricula.getCalificacionesList().get(1).getNota() + matricula.getCalificacionesList().get(2).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;
            case "Nota 3 P1":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(2).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(2);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(3);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(0).getNota() + matricula.getCalificacionesList().get(1).getNota() + matricula.getCalificacionesList().get(2).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;

            case "Nota 1 P2":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(4).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(4);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(7);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(4).getNota() + matricula.getCalificacionesList().get(5).getNota() + matricula.getCalificacionesList().get(6).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;

            case "Nota 2 P2":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(5).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(5);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(7);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(4).getNota() + matricula.getCalificacionesList().get(5).getNota() + matricula.getCalificacionesList().get(6).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;

            case "Nota 3 P2":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(6).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(6);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(7);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(4).getNota() + matricula.getCalificacionesList().get(5).getNota() + matricula.getCalificacionesList().get(6).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;
            case "Nota 1 P3":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(8).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(8);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(11);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(8).getNota() + matricula.getCalificacionesList().get(9).getNota() + matricula.getCalificacionesList().get(10).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;
            case "Nota 2 P3":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(9).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(9);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(11);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(8).getNota() + matricula.getCalificacionesList().get(9).getNota() + matricula.getCalificacionesList().get(10).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;
            case "Nota 3 P3":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (newValue != 0 && !newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(10).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(10);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(11);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(8).getNota() + matricula.getCalificacionesList().get(9).getNota() + matricula.getCalificacionesList().get(10).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((matricula.getCalificacionesList().get(3).getNota() + matricula.getCalificacionesList().get(7).getNota() + matricula.getCalificacionesList().get(11).getNota()) / 3).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;

            case "Nota Reposicion":
                matricula = (Matriculas) ((DataTable) event.getComponent()).getRowData();
                if (!newValue.equals(oldValue)) {
                    calificacion = new Calificaciones();
                    if (matricula.getCalificacionesList().get(12).getIdCalificacion() != null) {
                        calificacion = matricula.getCalificacionesList().get(12);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                        calificacion = matricula.getCalificacionesList().get(13);
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(new BigDecimal((calificacion.getNota() + newValue) / 2).setScale(2, RoundingMode.HALF_UP).doubleValue());

                        calificacion.setNota((calificacion.getNota() + newValue) / 2);
                        calificaionesEJB.edit(calificacion);
                    } else {
                        calificacion.setIdImpartir(impartir);
                        calificacion.setNota(newValue);
                        calificaionesEJB.edit(calificacion);
                    }

                    for (Matriculas matri : lstMatriculas) {
                        matri.setCalificacionesList(calificaionesEJB.obtenerCalificacionesPorMateria(matri, impartir));
                    }
                    calificacion = new Calificaciones();
                }
                break;
            default:
                throw new AssertionError();
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
