/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author said
 */
@Entity
@Table(name = "Coordinador_grado")

public class Coordinadorgrado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinador_grado")
    private Integer idCoordinadorGrado;
    @Column(name = "anio")
    private String anio;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCoordinadorGrado")
    private List<Matriculas> matriculasList;

    public Coordinadorgrado() {
    }

    public Coordinadorgrado(Integer idCoordinadorGrado) {
        this.idCoordinadorGrado = idCoordinadorGrado;
    }

    public Integer getIdCoordinadorGrado() {
        return idCoordinadorGrado;
    }

    public void setIdCoordinadorGrado(Integer idCoordinadorGrado) {
        this.idCoordinadorGrado = idCoordinadorGrado;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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
    public List<Matriculas> getMatriculasList() {
        return matriculasList;
    }

    public void setMatriculasList(List<Matriculas> matriculasList) {
        this.matriculasList = matriculasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoordinadorGrado != null ? idCoordinadorGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coordinadorgrado)) {
            return false;
        }
        Coordinadorgrado other = (Coordinadorgrado) object;
        if ((this.idCoordinadorGrado == null && other.idCoordinadorGrado != null) || (this.idCoordinadorGrado != null && !this.idCoordinadorGrado.equals(other.idCoordinadorGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Coordinadorgrado[ idCoordinadorGrado=" + idCoordinadorGrado + " ]";
    }
    
}
