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

/**
 *
 * @author said
 */
@Entity
@Table(name = "Encargados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encargados.findAll", query = "SELECT e FROM Encargados e")})
public class Encargados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encargado")
    private Integer idEncargado;
    @Size(max = 10)
    @Column(name = "dui")
    private String dui;
    @Size(max = 45)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Personas idPersona;


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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
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
