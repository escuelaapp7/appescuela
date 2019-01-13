/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author said
 */
@Entity
@Table(name = "calificaciones")
@NamedQueries({
    @NamedQuery(name = "Calificaciones.findAll", query = "SELECT c FROM Calificaciones c")})
public class Calificaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Integer idCalificacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje")
    private Double porcentaje;
    @Column(name = "nota")
    private Double nota;
    @JoinColumn(name = "id_impartir", referencedColumnName = "id_impartir")
    @ManyToOne(optional = false)
    private Impartir idImpartir;
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne(optional = false)
    private Matriculas idMatricula;
    @JoinColumn(name = "id_porcentaje_evaluacion", referencedColumnName = "id_porcentaje_evaluacion")
    @ManyToOne(optional = false)
    private PorcentajeEvaluacion idPorcentajeEvaluacion;

    public Calificaciones() {
    }

    public Calificaciones(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Impartir getIdImpartir() {
        return idImpartir;
    }

    public void setIdImpartir(Impartir idImpartir) {
        this.idImpartir = idImpartir;
    }

    public Matriculas getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matriculas idMatricula) {
        this.idMatricula = idMatricula;
    }

    public PorcentajeEvaluacion getIdPorcentajeEvaluacion() {
        return idPorcentajeEvaluacion;
    }

    public void setIdPorcentajeEvaluacion(PorcentajeEvaluacion idPorcentajeEvaluacion) {
        this.idPorcentajeEvaluacion = idPorcentajeEvaluacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacion != null ? idCalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificaciones)) {
            return false;
        }
        Calificaciones other = (Calificaciones) object;
        if ((this.idCalificacion == null && other.idCalificacion != null) || (this.idCalificacion != null && !this.idCalificacion.equals(other.idCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Calificaciones[ idCalificacion=" + idCalificacion + " ]";
    }
    
}
