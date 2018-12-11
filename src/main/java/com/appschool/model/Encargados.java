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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "Encargados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encargados.findAll", query = "SELECT e FROM Encargados e")
    , @NamedQuery(name = "Encargados.findByIdEncargado", query = "SELECT e FROM Encargados e WHERE e.idEncargado = :idEncargado")
    , @NamedQuery(name = "Encargados.findByDui", query = "SELECT e FROM Encargados e WHERE e.dui = :dui")})
public class Encargados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_encargado")
    private Integer idEncargado;
    @Size(max = 10)
    @Column(name = "dui")
    private String dui;
    @JoinColumn(name = "id_parentezco", referencedColumnName = "id_parentezco")
    @ManyToOne(optional = false)
    private Parentezcos idParentezco;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncargado")
    private List<Alumnos> alumnosList;

    public Encargados() {
    }

    public Encargados(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Integer getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(Integer idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Parentezcos getIdParentezco() {
        return idParentezco;
    }

    public void setIdParentezco(Parentezcos idParentezco) {
        this.idParentezco = idParentezco;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public List<Alumnos> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumnos> alumnosList) {
        this.alumnosList = alumnosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncargado != null ? idEncargado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encargados)) {
            return false;
        }
        Encargados other = (Encargados) object;
        if ((this.idEncargado == null && other.idEncargado != null) || (this.idEncargado != null && !this.idEncargado.equals(other.idEncargado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Encargados[ idEncargado=" + idEncargado + " ]";
    }
    
}
