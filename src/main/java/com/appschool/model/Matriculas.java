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
@Table(name = "Matriculas")

public class Matriculas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Integer idMatricula;
    @Column(name = "anio")
    @Temporal(TemporalType.DATE)
    private Date anio;
    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMatricula")
    private List<Impartir> impartirList;
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @ManyToOne(optional = false)
    private Alumnos idAlumno;
    @JoinColumn(name = "id_coordinador_grado", referencedColumnName = "id_coordinador_grado")
    @ManyToOne(optional = false)
    private Coordinadorgrado idCoordinadorGrado;
    @JoinColumn(name = "id_encargado", referencedColumnName = "id_encargado")
    @ManyToOne(optional = false)
    private Encargados idEncargado;
    @JoinColumn(name = "id_parentezco", referencedColumnName = "id_parentezco")
    @ManyToOne(optional = false)
    private Parentezcos idParentezco;

    public Matriculas() {
    }

    public Matriculas(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    @XmlTransient
    public List<Impartir> getImpartirList() {
        return impartirList;
    }

    public void setImpartirList(List<Impartir> impartirList) {
        this.impartirList = impartirList;
    }

    public Alumnos getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumnos idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Coordinadorgrado getIdCoordinadorGrado() {
        return idCoordinadorGrado;
    }

    public void setIdCoordinadorGrado(Coordinadorgrado idCoordinadorGrado) {
        this.idCoordinadorGrado = idCoordinadorGrado;
    }

    public Encargados getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Encargados idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Parentezcos getIdParentezco() {
        return idParentezco;
    }

    public void setIdParentezco(Parentezcos idParentezco) {
        this.idParentezco = idParentezco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matriculas)) {
            return false;
        }
        Matriculas other = (Matriculas) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Matriculas[ idMatricula=" + idMatricula + " ]";
    }
    
}
