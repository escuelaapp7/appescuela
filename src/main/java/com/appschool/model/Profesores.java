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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "Profesores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesores.findAll", query = "SELECT p FROM Profesores p")
    , @NamedQuery(name = "Profesores.findByIdProfesor", query = "SELECT p FROM Profesores p WHERE p.idProfesor = :idProfesor")
    , @NamedQuery(name = "Profesores.findByEscalafon", query = "SELECT p FROM Profesores p WHERE p.escalafon = :escalafon")
    , @NamedQuery(name = "Profesores.findByDui", query = "SELECT p FROM Profesores p WHERE p.dui = :dui")})
public class Profesores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_profesor")
    private Integer idProfesor;
    @Size(max = 10)
    @Column(name = "escalafon")
    private String escalafon;
    @Size(max = 10)
    @Column(name = "dui")
    private String dui;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfesor")
    private List<Impartir> impartirList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfesor")
    private List<Matriculas> matriculasList;

    public Profesores() {
    }

    public Profesores(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getEscalafon() {
        return escalafon;
    }

    public void setEscalafon(String escalafon) {
        this.escalafon = escalafon;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    @XmlTransient
    public List<Impartir> getImpartirList() {
        return impartirList;
    }

    public void setImpartirList(List<Impartir> impartirList) {
        this.impartirList = impartirList;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
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
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesores)) {
            return false;
        }
        Profesores other = (Profesores) object;
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Profesores[ idProfesor=" + idProfesor + " ]";
    }
    
}
