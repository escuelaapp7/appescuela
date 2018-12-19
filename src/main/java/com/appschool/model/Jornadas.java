/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appschool.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Jornadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jornadas.findAll", query = "SELECT j FROM Jornadas j")
    , @NamedQuery(name = "Jornadas.findByIdJornada", query = "SELECT j FROM Jornadas j WHERE j.idJornada = :idJornada")
    , @NamedQuery(name = "Jornadas.findByDescripcion", query = "SELECT j FROM Jornadas j WHERE j.descripcion = :descripcion")})
public class Jornadas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jornada")
    private Integer idJornada;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;

    public Jornadas() {
    }

    public Jornadas(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJornada != null ? idJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jornadas)) {
            return false;
        }
        Jornadas other = (Jornadas) object;
        if ((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.appschool.model.Jornadas[ idJornada=" + idJornada + " ]";
    }

}
