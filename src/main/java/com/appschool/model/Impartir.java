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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author said
 */
@Entity
@Table(name = "Impartir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impartir.findAll", query = "SELECT i FROM Impartir i")
    , @NamedQuery(name = "Impartir.findByIdImpartir", query = "SELECT i FROM Impartir i WHERE i.idImpartir = :idImpartir")})
public class Impartir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_impartir")
    private Integer idImpartir;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne(optional = false)
    private Asignaturas idAsignatura;
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne(optional = false)
    private Matriculas idMatricula;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    @ManyToOne(optional = false)
    private Profesores idProfesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImpartir")
    private List<Calificaciones> calificacionesList;

    public Impartir() {
    }

    public Impartir(Integer idImpartir) {
        this.idImpartir = idImpartir;
    }

    public Integer getIdImpartir() {
        return idImpartir;
    }

    public void setIdImpartir(Integer idImpartir) {
        this.idImpartir = idImpartir;
    }

    public Asignaturas getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignaturas idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Matriculas getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matriculas idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Profesores getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesores idProfesor) {
        this.idProfesor = idProfesor;
    }

    @XmlTransient
    public List<Calificaciones> getCalificacionesList() {
        return calificacionesList;
    }

    public void setCalificacionesList(List<Calificaciones> calificacionesList) {
        this.calificacionesList = calificacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImpartir != null ? idImpartir.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impartir)) {
            return false;
        }
        Impartir other = (Impartir) object;
        if ((this.idImpartir == null && other.idImpartir != null) || (this.idImpartir != null && !this.idImpartir.equals(other.idImpartir))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Impartir[ idImpartir=" + idImpartir + " ]";
    }
    
}
