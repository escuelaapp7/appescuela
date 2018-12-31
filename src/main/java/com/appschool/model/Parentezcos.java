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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author said
 */
@Entity
@Table(name = "Parentezcos")

public class Parentezcos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parentezco")
    private Integer idParentezco;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParentezco")
    private List<Matriculas> matriculasList;

    public Parentezcos() {
    }

    public Parentezcos(Integer idParentezco) {
        this.idParentezco = idParentezco;
    }

    public Integer getIdParentezco() {
        return idParentezco;
    }

    public void setIdParentezco(Integer idParentezco) {
        this.idParentezco = idParentezco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idParentezco != null ? idParentezco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parentezcos)) {
            return false;
        }
        Parentezcos other = (Parentezcos) object;
        if ((this.idParentezco == null && other.idParentezco != null) || (this.idParentezco != null && !this.idParentezco.equals(other.idParentezco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Parentezcos[ idParentezco=" + idParentezco + " ]";
    }
    
}
