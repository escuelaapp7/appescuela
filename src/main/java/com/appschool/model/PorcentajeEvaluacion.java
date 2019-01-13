/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author said
 */
@Entity
@Table(name = "porcentaje_evaluacion")
@NamedQueries({
    @NamedQuery(name = "PorcentajeEvaluacion.findAll", query = "SELECT p FROM PorcentajeEvaluacion p")})
public class PorcentajeEvaluacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_porcentaje_evaluacion")
    private Integer idPorcentajeEvaluacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje")
    private Double porcentaje;
    @JoinColumn(name = "id_evaluacion", referencedColumnName = "id_evaluacion")
    @ManyToOne(optional = false)
    private Evaluaciones idEvaluacion;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne(optional = false)
    private Periodos idPeriodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPorcentajeEvaluacion")
    private List<Calificaciones> calificacionesList;

    public PorcentajeEvaluacion() {
    }

    public PorcentajeEvaluacion(Integer idPorcentajeEvaluacion) {
        this.idPorcentajeEvaluacion = idPorcentajeEvaluacion;
    }

    public Integer getIdPorcentajeEvaluacion() {
        return idPorcentajeEvaluacion;
    }

    public void setIdPorcentajeEvaluacion(Integer idPorcentajeEvaluacion) {
        this.idPorcentajeEvaluacion = idPorcentajeEvaluacion;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Evaluaciones getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Evaluaciones idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Periodos getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodos idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public List<Calificaciones> getCalificacionesList() {
        return calificacionesList;
    }

    public void setCalificacionesList(List<Calificaciones> calificacionesList) {
        this.calificacionesList = calificacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPorcentajeEvaluacion != null ? idPorcentajeEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorcentajeEvaluacion)) {
            return false;
        }
        PorcentajeEvaluacion other = (PorcentajeEvaluacion) object;
        if ((this.idPorcentajeEvaluacion == null && other.idPorcentajeEvaluacion != null) || (this.idPorcentajeEvaluacion != null && !this.idPorcentajeEvaluacion.equals(other.idPorcentajeEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.PorcentajeEvaluacion[ idPorcentajeEvaluacion=" + idPorcentajeEvaluacion + " ]";
    }
    
}
