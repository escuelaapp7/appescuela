/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Impartir.findAll", query = "SELECT i FROM Impartir i")})
public class Impartir implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_impartir")
    private Integer idImpartir;
    @Column(name = "anio")
    private Integer anio;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne(optional = false)
    private Asignaturas idAsignatura;
    @JoinColumn(name = "id_grado", referencedColumnName = "id_grado")
    @ManyToOne(optional = false)
    private Grados idGrado;
    @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada")
    @ManyToOne(optional = false)
    private Jornadas idJornada;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    @ManyToOne(optional = false)
    private Profesores idProfesor;
    @JoinColumn(name = "id_seccion", referencedColumnName = "id_seccion")
    @ManyToOne(optional = false)
    private Secciones idSeccion;
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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Asignaturas getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignaturas idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Grados getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grados idGrado) {
        this.idGrado = idGrado;
    }

    public Jornadas getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Jornadas idJornada) {
        this.idJornada = idJornada;
    }

    public Profesores getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesores idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Secciones getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Secciones idSeccion) {
        this.idSeccion = idSeccion;
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
