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
@Table(name = "Grados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grados.findAll", query = "SELECT g FROM Grados g")})
public class Grados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado")
    private Integer idGrado;
    @Size(max = 25)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrado")
    private List<Impartir> impartirList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrado")
    private List<Coordinadorgrado> coordinadorGradoList;

    public Grados() {
    }

    public Grados(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Impartir> getImpartirList() {
        return impartirList;
    }

    public void setImpartirList(List<Impartir> impartirList) {
        this.impartirList = impartirList;
    }

    @XmlTransient
    public List<Coordinadorgrado> getCoordinadorGradoList() {
        return coordinadorGradoList;
    }

    public void setCoordinadorGradoList(List<Coordinadorgrado> coordinadorGradoList) {
        this.coordinadorGradoList = coordinadorGradoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrado != null ? idGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grados)) {
            return false;
        }
        Grados other = (Grados) object;
        if ((this.idGrado == null && other.idGrado != null) || (this.idGrado != null && !this.idGrado.equals(other.idGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Grados[ idGrado=" + idGrado + " ]";
    }
    
}
