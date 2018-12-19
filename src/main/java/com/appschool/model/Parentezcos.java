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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name = "Parentezcos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parentezcos.findAll", query = "SELECT p FROM Parentezcos p")
    , @NamedQuery(name = "Parentezcos.findByIdParentezco", query = "SELECT p FROM Parentezcos p WHERE p.idParentezco = :idParentezco")
    , @NamedQuery(name = "Parentezcos.findByDescripcion", query = "SELECT p FROM Parentezcos p WHERE p.descripcion = :descripcion")})
public class Parentezcos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parentezco")
    private Integer idParentezco;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;


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
